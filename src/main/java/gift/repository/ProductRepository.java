package gift.repository;

import gift.model.Product;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Product save(Product product) {
        products.put(product.id(), product);
        return product;
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
}
