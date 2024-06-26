package gift.application;

import gift.dao.ProductRepository;
import gift.domain.Product;

import gift.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        List<ProductResponse> responseList = new ArrayList<>();
        for (Product product : productList) {
            responseList.add(new ProductResponse(product));
        }
        return responseList;
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

}