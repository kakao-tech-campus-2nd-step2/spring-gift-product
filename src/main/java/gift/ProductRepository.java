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

    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        products.put(id, product);
        return products.get(id);
    }

    public Long deleteProduct(Long id) {
        products.remove(id);
        return id;
    }

    public boolean isExist(Long id) {
        return products.containsKey(id);
    }
    public Product findProductById(Long id) {
        return products.get(id);
    }
}
