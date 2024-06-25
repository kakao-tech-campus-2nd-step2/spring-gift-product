package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> productMap = new HashMap<>();

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productMap.values()
                .stream()
                .toList();
    }
}
