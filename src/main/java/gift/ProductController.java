package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    @GetMapping
    public Map<Long, Product> getAllProducts() {
        return products;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product receivedData) {
        long id = nextId++;
        Product product = new Product(id, receivedData.getName(), receivedData.getPrice(), receivedData.getImageUrl());
        products.put(id, product);
        return product;
    }
}
