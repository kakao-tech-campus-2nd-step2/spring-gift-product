package gift.repository;

import gift.model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

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

    @Override
    public List<Product> findPaginated(int page, int size) {
        int start = page * size;
        int end = Math.min((page + 1) * size, products.size());
        return products.values().stream()
            .sorted(Comparator.comparing(Product::getId))
            .collect(Collectors.toList())
            .subList(start, end);
    }
}
