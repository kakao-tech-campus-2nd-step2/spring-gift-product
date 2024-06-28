package gift.repository;

import gift.Product;
import java.util.List;
import java.util.Map;

public interface ProductRepository {

    Product save(Product product);

    boolean delete(Long id);

    boolean edit(Long id, Product product);

    Product findById(Long id);

    List<Product> findAll();
}