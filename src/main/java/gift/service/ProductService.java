package gift.service;

import gift.domain.Product;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1;

    public ArrayList<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public boolean exists(Long id) {
        return products.containsKey(id);
    }

    public Optional<Product> find(Long id) {
        Product product = products.get(id);
        return Optional.ofNullable(product);
    }

    public Optional<Product> save(Product product) {
        if (products.containsKey(product.id())) {
            return Optional.empty();
        }
        product = new Product(currentId++, product.name(), product.price(), product.imageUrl());
        products.put(product.id(), product);
        return Optional.of(product);
    }

    public Optional<Product> update(Long id, Product product) {
        if (!products.containsKey(id)) {
            return Optional.empty();
        }
        product = new Product(id, product.name(), product.price(), product.imageUrl());
        products.put(id, product);
        return Optional.of(product);
    }

    public Optional<Product> delete(Long id) {
        if (!products.containsKey(id)) {
            return Optional.empty();
        }
        Optional<Product> product = Optional.of(products.get(id));
        products.remove(id);
        return product;
    }
}
