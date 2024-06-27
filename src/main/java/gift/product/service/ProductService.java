package gift.product.service;

import gift.product.Product;
import gift.product.repository.ProductMemoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductMemoryRepository productMemoryRepository;

    @Autowired
    public ProductService(ProductMemoryRepository productMemoryRepository) {
        this.productMemoryRepository = productMemoryRepository;
    }

    public List<Product> findAllProducts() {
        return productMemoryRepository.findAllProducts();
    }

    public Product createProduct(Product product) {
        return productMemoryRepository.createProduct(product);
    }

    public Product updateProduct(Long id, Product product) {
        if(!isExist(id)) {
            return createProduct(product);
        }
        return productMemoryRepository.updateProduct(id, product);
    }

    public Long deleteProduct(Long id) {
        if(!isExist(id)) {
            // 예외처리 학습후 반환값 수정 예정
            return -1L;
        }
        return productMemoryRepository.deleteProduct(id);
    }

    public Product getProductById(Long id) {
        return productMemoryRepository.findProductById(id);
    }

    private boolean isExist(Long id) {
        return productMemoryRepository.isExist(id);
    }
}
