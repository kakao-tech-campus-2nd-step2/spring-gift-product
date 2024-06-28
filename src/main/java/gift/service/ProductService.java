package gift.service;


import gift.domain.Product;
import gift.repository.ProductH2Repository;
import gift.repository.ProductMemoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
//    private final ProductMemoryRepository productMemoryRepository;
    private final ProductH2Repository productH2Repository;

//    public ProductService(ProductMemoryRepository productMemoryRepository) {
//        this.productMemoryRepository = productMemoryRepository;
//    }
    public ProductService(ProductH2Repository productH2Repository) {
        this.productH2Repository = productH2Repository;
    }

    public Product getProductById(Long id) {
        return productH2Repository.findById(id).orElse(null);
    }

    public Iterable<Product> getAllProducts() {
        return productH2Repository.findAll();
    }

    public Product createProduct(Product product) {
        Product product1 = productH2Repository.save(product);
        return product1;
    }

    public void updateProduct(Long id, Product updatedProduct) {
        productH2Repository.update(id, updatedProduct);
    }

    public void deleteProduct(Long id) {
        productH2Repository.deleteById(id);
//        productH2Repository.orderId();
    }
}
