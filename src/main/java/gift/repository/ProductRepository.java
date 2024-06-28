package gift.repository;

import gift.controller.ProductDto;
import gift.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    List<Product> findAll();
    Optional<Product> updateById(Long id, ProductDto productDto);
    Optional<Product> deleteById(Long id);
}
