package gift.application;

import gift.dao.ProductRepository;
import gift.domain.Product;

import gift.dto.ProductRequest;
import gift.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 상품은 존재하지 않습니다")
        );
        return new ProductResponse(product);
    }

    public ProductResponse createProduct(ProductRequest request) {
        Product product = new Product(request);
        return new ProductResponse(productRepository.save(product));
    }

    public Long deleteProductById(Long id) {
        productRepository.deleteById(id);
        return id;
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }

    public Long updateProduct(Long id, ProductRequest request) {
        if (!id.equals(request.id())) {
            deleteProductById(id);
            createProduct(request);
            return request.id();
        }
        Product product = productRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 상품은 존재하지 않습니다")
        );
        product.update(request);
        return product.getId();
    }

}