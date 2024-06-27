package gift;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("")
    public Map<Long, Product> getAllProducts() {
        return products;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return products.get(id);
    }

    @PostMapping("")
    public void createProduct(@RequestBody Product product) {
        products.put(product.id(), product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (products.containsKey(id)) {
            products.put(id, product);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }
}
