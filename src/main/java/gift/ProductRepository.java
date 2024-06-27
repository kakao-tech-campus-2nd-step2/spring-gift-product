package gift;

import java.util.List;

public interface ProductRepository {

    Product get(Long id);

    void save(Long id, Product product);

    int size();

    List<Product> findAll();

    void remove(Long id);

}
