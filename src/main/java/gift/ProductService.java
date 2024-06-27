package gift;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1;

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
//        if (products.containsKey(product.getId())) {//원래도 존재하는 id면 메세지 반환
//            return "이미 존재하는 id 입니다.";
//        }
        product.setId(currentId++);
        products.put(product.getId(), product);
//        return null; // 성공 시 null 반환
    }

    public void deleteProduct(Long id) {
//        if (products.remove(id) != null) {
//            return null; // 성공 시 null 반환
//        }
//        return "상품이 없습니다.";
        products.remove(id);
    }

    public void modifyProduct(Long id, Product product) {
//        if (products.containsKey(id)) {
//            products.put(id, product);
//            return product;
//        }
//
//        return null;
        product.setId(id);
        products.put(id, product);
    }


}
