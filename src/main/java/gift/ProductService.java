package gift;

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