package gift;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return products
                .values()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }
}
