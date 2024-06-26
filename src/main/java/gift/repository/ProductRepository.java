package gift.repository;

import gift.product.Product;
import gift.product.ProductRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public class ProductRepository {
    private final AtomicLong atomicLong = new AtomicLong(0);
    private final Map<Long, Product> products = new HashMap<>();


    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(long id) {
        if (!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return products.get(id);
    }

    public Product insert(ProductRequest productRequest) {
        long id = atomicLong.incrementAndGet();
        Product product = productRequest.toProduct(id);
        products.put(id, product);
        return product;
    }

    public Product update(long id, ProductRequest productRequest) {
        if (!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Product product = productRequest.toProduct(id);
        products.put(id, product);
        return product;
    }

    public void delete(long id) {
        if (!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        products.remove(id);
    }
}
