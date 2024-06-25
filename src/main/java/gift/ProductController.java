package gift;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    @PostMapping
    public Product setProduct(@RequestBody Product product) {
        products.put(product.id(), product);
        return  product;
    }
    @PutMapping("/{id}")
    public Object updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if(products.containsKey(id)){
            products.put(id, product);
            return  product;
        }
        return "Update failed.";
    }

    // 특정 상품 조회를 위한 GET 메서드
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return products.get(id);
    }

    // 전체 상품 리스트 조회를 위한 GET 메서드
    @GetMapping
    public Collection<Product> getAllProducts() {
        return products.values();
    }
    @DeleteMapping("/{id}")
    public void DeleteProduct(@PathVariable Long id){
        products.remove(id);
    }
}
