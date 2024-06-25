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
    // 특정 상품 조회를 위한 GET 메서드
    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable Long id) {
        return products.get(id);
    }

    // 전체 상품 리스트 조회를 위한 GET 메서드
    @GetMapping("/products")
    public Map<Long, Product> getAllProducts() {
        return products;
    }
    @DeleteMapping("/products/{id}")
    public void DeleteProduct(@PathVariable Long id){
        products.remove(id);
    }
}
