package gift;

import java.util.List;

public interface ProductRepository {
    Product insert(Product product);

    List<Product> findAll();

    Product findById(Long id);

    Product update(Product product);

    void deleteById(Long id);

    boolean existsById(Long id);

}
