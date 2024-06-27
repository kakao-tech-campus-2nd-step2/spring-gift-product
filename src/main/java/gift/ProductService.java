package gift;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        if(!productRepository.isExistProductId(id)){
            throw new NoSuchElementException("존재하지 않는 상품입니다.");
        }
        return productRepository.getProductById(id);
    }
}
