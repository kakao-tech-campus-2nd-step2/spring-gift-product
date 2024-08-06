package gift.service;

import gift.domain.Product;
import gift.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.selectAllProducts();
    }

    public boolean exists(Long id) {
        return productRepository.selectProductById(id) != null;
    }

    public Optional<Product> find(Long id) {
        return Optional.ofNullable(productRepository.selectProductById(id));
    }

    public Optional<Product> save(Product product) {
        Product newProduct = new Product(productRepository.getNextId(), product.name(), product.price(), product.imageUrl());
        productRepository.insertProduct(newProduct);
        return Optional.of(newProduct);
    }

    public Optional<Product> update(Long id, Product product) {
        product = new Product(id, product.name(), product.price(), product.imageUrl());
        productRepository.updateProduct(product);
        return Optional.of(product);
    }

    public Optional<Product> delete(Long id) {
        Optional<Product> product = find(id);
        productRepository.deleteProductById(id);
        return product;
    }
}
