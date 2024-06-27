package repository;

import model.Product;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class ProductRepository {
  private final Map<Long, Product> productMap = new HashMap<>();
  private long counter = 0;

  public synchronized List<Product> findAll(){
    return new ArrayList<>((productMap.values()));
  }
  public synchronized Optional<Product> findById(Long id){
    return Optional.ofNullable(productMap.get(id));
  }
  public synchronized Product save(Product product){
    if(product.getId() == null){
      product.setId(++counter);
    }
    productMap.put(product.getId(), product);
    return product;
  }
  public synchronized void deleteById(Long id){
    productMap.remove(id);
  }
}
