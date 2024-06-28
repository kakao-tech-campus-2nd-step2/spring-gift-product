package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long counter = 0L;

    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product createProduct(Product product) {
        Long productId = ++counter;
        product.setId(productId);
        products.put(productId, product);
        return products.get(productId);
    }
}