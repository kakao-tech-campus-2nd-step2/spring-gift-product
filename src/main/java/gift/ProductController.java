package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public ProductController(){
        products.put(counter.incrementAndGet(), new Product(counter.get(), "아이스 카페 아메리카노 T", 4500, "url1"));
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }
}