package gift.product.service;

import gift.product.Product;
import gift.product.repository.MemoryProductRepository;
import gift.product.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAllProducts() {
        return productRepository.findAllProducts();
    }

    public Product createProduct(Product product) {
        return productRepository.createProduct(product);
    }

    public Product updateProduct(Long id, Product product) {
        if(!isExist(id)) {
            return createProduct(product);
        }
        return productRepository.updateProduct(id, product);
    }

    public Long deleteProduct(Long id) {
        if(!isExist(id)) {
            // 예외처리 학습후 반환값 수정 예정
            return -1L;
        }
        return productRepository.deleteProduct(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    private boolean isExist(Long id) {
        return productRepository.isExist(id);
    }
}
