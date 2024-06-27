package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @PostMapping
    public Product addProduct(@RequestBody Product receivedData) {
        long id = nextId++;
        Product product = new Product(id, receivedData.getName(), receivedData.getPrice(), receivedData.getImageUrl());
        products.put(id, product);
        return product;
    }
}
