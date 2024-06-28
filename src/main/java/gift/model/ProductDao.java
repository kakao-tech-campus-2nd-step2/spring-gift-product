package gift.model;

import java.util.List;

public interface ProductDao {

    Product save(ProductRequest productRequest);

    Product findById(Long id);

    List<Product> findAll();

    Product update(Long id, ProductRequest productRequest);

    void delete(Long id);
}
