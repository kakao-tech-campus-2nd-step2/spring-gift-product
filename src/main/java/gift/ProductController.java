package gift;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping
    public Collection<Product> getProducts() {
        return products.values();
    }
}
