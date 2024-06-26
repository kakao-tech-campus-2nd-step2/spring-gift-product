package repository;

import domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long id = 0L;

    public Product save(Product product){
        id += 1;
        product.setId(id);
        products.put(id, product);
        return product;
    }

    public Optional<Product> findById(Long id){
        return Optional.ofNullable(products.get(id));
    }

    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public Long delete(Long id){
        products.remove(id);
        return id;
    }
}
