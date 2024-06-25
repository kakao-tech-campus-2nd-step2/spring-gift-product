package gift.dao;

import gift.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    void save(Long id, Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void deleteById(Long id);
    void deleteAll();
}
