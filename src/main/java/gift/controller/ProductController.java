package gift.controller;

import gift.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productsRes = new ArrayList<>(products.values());
        return ResponseEntity.ok().body(productsRes);
    }

    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam Long id) {
        Product product = products.get(id);
        return ResponseEntity.ok().body(product);
    }
}
