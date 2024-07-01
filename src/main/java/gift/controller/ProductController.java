package gift.controller;

import gift.dto.ProductRequest;
import gift.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public Collection<Product> getAllProducts() {
        return products.values();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        return products.get(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody ProductRequest productRequest) {
        long id = idGenerator.incrementAndGet();
        Product product = new Product(id, productRequest.name(), productRequest.price(), productRequest.imageUrl());
        products.put(id, product);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        Product product = new Product(id, productRequest.name(), productRequest.price(), productRequest.imageUrl());
        products.put(id, product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        products.remove(id);
    }
}
