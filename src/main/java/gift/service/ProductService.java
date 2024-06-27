package gift.service;

import gift.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
  private final Map<Long, Product> products = new HashMap<>();
  private Long nextId = 1L;

  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }

  public Optional<Product> findById(Long id) {
    return Optional.ofNullable(products.get(id));
  }

  public Product save(Product product) {
    if (product.getId() == null) {
      product.setId(nextId++);
    }
    products.put(product.getId(), product);
    return product;
  }

  public void deleteById(Long id) {
    products.remove(id);
  }
}
