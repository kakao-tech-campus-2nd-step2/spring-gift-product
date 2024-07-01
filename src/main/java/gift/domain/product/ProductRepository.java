package gift.domain.product;

import java.util.ArrayList;

public interface ProductRepository {
    void save(Product product);
    ArrayList<Product> findAll();
    Product findById(Long id);
    void deleteById(Long id);
}
