package gift.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long currentId = 1L;

    public List<Product> findAll() {
        return products.values().stream().toList();
    }

    public Product save(Product product) {
        product.setId(currentId);
        products.put(currentId, product);
        currentId++;

        return product;
    }

    public void clear() {
        products.clear();
        currentId = 0L;
    }

}
