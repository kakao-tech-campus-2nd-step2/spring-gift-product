package gift;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long sequence = 1;

    @GetMapping
    public Collection<Product> getProducts() {
        return products.values();
    }

    @GetMapping("/{sequence}")
    public Product getProduct(@PathVariable("sequence") Long sequence) {
        Product product = products.get(sequence);
        if (product == null) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        return product;
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        if (products.containsValue(product)) {
            throw new IllegalArgumentException("이미 존재하는 상품입니다.");
        }
        products.put(sequence, product);
        sequence++;
        return product;
    }
}
