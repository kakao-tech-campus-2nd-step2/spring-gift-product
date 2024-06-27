package gift.service;

import gift.controller.request.ProductRequest;
import gift.domain.Product;
import gift.domain.ProductRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    public void addProduct(ProductRequest request) {
        productRepository.save(request.toEntity());
    }

    public void editProduct(Long productId, ProductRequest request) {
        productRepository.edit(productId, request.toEntity());
    }

    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }

}
