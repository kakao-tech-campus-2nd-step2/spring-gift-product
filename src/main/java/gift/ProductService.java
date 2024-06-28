package gift;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //모든 제품 검색 후 list<product>로 반환
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    // 해당 id 가진 제품 검색후 product객체로 반환
    public Product getProductById(Long id) {
        return productRepository.findById(id);
    }




}