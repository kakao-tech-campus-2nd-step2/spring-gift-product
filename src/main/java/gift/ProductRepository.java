package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final static Map<Long, Product> products = new HashMap<>();

    public boolean isExistProductId(Long id) {
        return products.containsKey(id);
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }
}
