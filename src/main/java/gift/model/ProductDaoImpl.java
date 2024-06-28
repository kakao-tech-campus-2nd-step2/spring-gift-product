package gift.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoImpl implements ProductDao {

    private static final Map<Long, Product> store = new HashMap<>();
    private static long sequence = 0L;

    public Product save(ProductRequest productRequest) {
        Product product = productRequest.toEntity(++sequence);
        store.put(sequence, product);
        return product;
    }

    public Product findById(Long id) {
        if (!store.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        return store.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Product update(Long id, ProductRequest productRequest) {
        if (!store.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        Product product = store.get(id);
        product.updateProduct(productRequest);

        return product;
    }

    public void delete(Long id) {
        if (!store.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        store.remove(id);
    }
}
