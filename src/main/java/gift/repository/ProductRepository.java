package gift.repository;

import gift.domain.Product;
import java.util.List;

public interface ProductRepository {

    Product get(Long id);

    boolean exists(Long id);

    void save(Product product);

    int size();

    List<Product> findAll();

    void remove(Long id);
}
