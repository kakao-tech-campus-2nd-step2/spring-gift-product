package gift.controller;

import gift.model.Product;
import gift.model.ProductRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productsRes = new ArrayList<>(products.values());
        return ResponseEntity.ok().body(productsRes);
    }

    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam("id") long id) {
        Product product = products.get(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@RequestBody ProductRequest request) {
        long id = idGenerator.incrementAndGet();
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }
}
