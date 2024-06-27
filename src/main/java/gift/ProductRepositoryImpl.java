package gift;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    @Override
    public Product get(Long id) {
        return products.get(id);
    }

    @Override
    public void save(Long id, Product product) {
        products.put(id, product);
    }

    @Override
    public int size() {
        return products.size();
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(products.values());
    }

    @Override
    public void remove(Long id) {
        products.remove(id);
    }
}
