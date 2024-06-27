package gift.repository;

import gift.domain.Product;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private Long Id = 1L;

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Iterable<Product> findAll() {
        return products.values();
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(Id++);
        }
        products.put(product.getId(), product);
        return product;
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
}


