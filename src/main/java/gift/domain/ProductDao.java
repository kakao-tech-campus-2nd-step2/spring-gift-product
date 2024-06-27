package gift.domain;

import java.util.List;

public interface ProductDao {
    void Save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void update(Product product);
    void deleteById(Long id);


}
