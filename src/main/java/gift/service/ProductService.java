package gift.service;

import gift.model.Product;
import gift.repository.ProductRepository;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public void addProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        productRepository.insertProduct(product);
    }

    public Product getProduct(Long id) {
        return productRepository.selectProduct(id);
    }

    public Map<Long, Product> getAllProducts() {
        return productRepository.selectAllProducts();
    }

    @Transactional
    public void updateProductDetail(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (!productRepository.existsById(product.getId())) {
            throw new IllegalArgumentException("Product not found with id: " + product.getId());
        }
        productRepository.updateProduct(product);
    }

    @Transactional
    public void deleteProductById(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
        productRepository.deleteProduct(id);
    }

    public boolean isProductsRepositoryEmpty() {
        return productRepository.selectAllProducts().isEmpty();
    }


    public boolean isExistProduct(Product product) {
        return productRepository.existsById(product.getId());
    }
}