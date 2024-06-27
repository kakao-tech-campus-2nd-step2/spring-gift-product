package gift;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public String addProduct(Product product) {
        if (products.containsKey(product.getId())) {//원래도 존재하는 id면 메세지 반환
            return "이미 존재하는 id 입니다.";
        }
        products.put(product.getId(), product);
        return null; // 성공 시 null 반환
    }
}
