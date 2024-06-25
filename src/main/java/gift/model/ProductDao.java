package gift.model;

import gift.controller.ProductRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductDao {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Product updateById(long id, ProductRequest request) {
        Product newProduct = new Product(id, request.name(), request.price(), request.imageUrl());
        products.replace(id, newProduct);
        return newProduct;
    }

    public Long save(ProductRequest request) {
        long id = idGenerator.incrementAndGet();
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return id;
    }

    public Product findById(long id) {
        return products.get(id);
    }


    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public void deleteById(long id) {
        products.remove(id);
    }
}
