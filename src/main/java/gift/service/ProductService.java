package gift.service;
import gift.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1;

    // 모든 제품 조회
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // 특정 제품 조회
    public Product getProductById(Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new NoSuchElementException("Product not found with id " + id);
        }
        return product;
    }

    // 제품 추가
    public Product addProduct(Product product) {
        product.setId(currentId++);
        products.put(product.getId(), product);
        return product;
    }

}