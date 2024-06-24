package gift.repository;

import gift.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductMemoryRepository implements ProductRepository{

    private static Map<Long, Product> products = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        products.put(sequence, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Long delete(Long id) {
        products.remove(id);
        return id;
    }
}
