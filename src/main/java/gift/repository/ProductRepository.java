package gift.repository;

import gift.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(Long id);

    boolean exists(Long id);

    void save(Product product);

    int size();

    List<Product> findAll();

    void remove(Long id);
}
