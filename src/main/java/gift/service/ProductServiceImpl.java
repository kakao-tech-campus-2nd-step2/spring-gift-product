package gift.service;

import gift.exception.ProductNotFoundException;
import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return product;
    }


    @Override
    public boolean createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());
            return productRepository.update(existingProduct);
        }
        return false;
    }

    @Override
    public boolean patchProduct(Long id, Map<String, Object> updates) {
        Product existingProduct = productRepository.findById(id);
        if (existingProduct != null) {
            applyUpdates(existingProduct, updates);
            return productRepository.update(existingProduct);
        }
        return false;
    }

    @Override
    public List<Product> patchProducts(List<Map<String, Object>> updatesList) {
        List<Product> updatedProducts = new ArrayList<>();
        for (Map<String, Object> updates : updatesList) {
            Long id = ((Number) updates.get("id")).longValue();
            if (patchProduct(id, updates)) {
                updatedProducts.add(productRepository.findById(id));
            }
        }
        return updatedProducts;
    }

    private void applyUpdates(Product product, Map<String, Object> updates) {
        updates.forEach((key, value) -> {
            if (!"id".equals(key)) {
                updateProductField(product, key, value);
            }
        });
    }

    private void updateProductField(Product product, String key, Object value) {
        if ("name".equals(key)) {
            product.setName((String) value);
            return;
        }
        if ("price".equals(key)) {
            product.setPrice((Integer) value);
            return;
        }
        if ("imageUrl".equals(key)) {
            product.setImageUrl((String) value);
            return;
        }
        throw new IllegalArgumentException("Invalid field: " + key);
    }


    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.delete(id);
    }

    public List<Product> getProducts(int page, int size) {
        return productRepository.findPaginated(page, size);
    }
}