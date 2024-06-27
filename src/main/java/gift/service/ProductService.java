package gift.service;


import gift.domain.Product;
import gift.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
            .map(product -> {
                product.setName(productDetails.getName());
                product.setPrice(productDetails.getPrice());
                product.setImageUrl(productDetails.getImageUrl());
                return productRepository.save(product);
            })
            .orElseGet(() -> {
                productDetails.setId(id);
                return productRepository.save(productDetails);
            });
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
