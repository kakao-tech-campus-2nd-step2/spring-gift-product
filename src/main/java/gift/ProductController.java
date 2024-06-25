package gift;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    @PutMapping("/products/{id}")
    public Product setProduct(@PathVariable Long id, @RequestBody Product product) {
        products.put(id, product);
        return product;
    }
}
