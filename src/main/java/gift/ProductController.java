package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        long id = nextId++;
        product.setId(id);
        products.put(id, product);
        return product;
    }
}
