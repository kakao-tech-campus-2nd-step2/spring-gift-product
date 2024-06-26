package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    public ProductController() {
        // Sample data
        products.put(nextId, new Product(nextId++, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> productList = new ArrayList<>(products.values());
        return ResponseEntity.ok(productList);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        products.put(id, product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        products.remove(id);
        return ResponseEntity.noContent().build();
    }
}