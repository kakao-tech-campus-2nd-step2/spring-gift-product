package gift.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    private static final Map<Long, Product> store = new HashMap<>();
    private static long sequence = 0L;

    public Product save(ProductDTO productDTO) {
        return productDTO.toEntity(++sequence);
    }

    public Product findById(Long id) {
        return store.get(id);
    }

    public List<Product> findAll() {
        return new ArrayList<>(store.values());
    }

    public Product update(Long id, ProductDTO productDTO) {
        Product product = store.get(id);
        product.updateProduct(productDTO);

        return product;
    }

    public void delete(Long id) {
        store.remove(id);
    }
}
