package gift.repository;

import gift.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(Long id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        long id = counter.incrementAndGet();
        product.setId(id);
        products.put(id, product);
    }

    @Override
    public void update(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void delete(Long id) {
        products.remove(id);
    }
}
