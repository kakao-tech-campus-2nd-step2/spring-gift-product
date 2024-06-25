package gift;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @PostMapping
    public Product addProduct(@RequestBody Map<String, Object> productData) {
        Long id = nextId++;
        String name = (String) productData.get("name");
        int price = (Integer) productData.get("price");
        String imageUrl = (String) productData.get("imageUrl");
        Product product = new Product(id, name, price, imageUrl);
        products.put(id, product);
        return product;
    }
}
