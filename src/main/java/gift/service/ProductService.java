package gift.service;

import gift.controller.request.ProductCreateRequest;
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

    public Product addProduct(ProductCreateRequest request) {
        return productRepository.save(request.toEntity());
    }

}
