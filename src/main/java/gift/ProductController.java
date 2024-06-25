package gift;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    // 새로운 상품 등록
    @PostMapping
    public Product setProduct(@RequestBody Product product) {
        products.put(product.id(), product);
        return  product;
    }
    // 등록된 상품 업데이트
    @PutMapping("/{id}")
    public Object updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if(products.containsKey(id)){
            products.put(id, product);
            return  product;
        }
        return "Update failed";
    }

    // 등록된 특정 상품 조회
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return products.get(id);
    }

    // 등록된 전체 상품 리스트 조회
    @GetMapping
    public Collection<Product> getAllProducts() {
        return products.values();
    }
    // 등록된 상품 삭제
    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable Long id){
        if(products.containsKey(id)) {
            products.remove(id);
            return "Delete Successful";
        }
        return "Delete failed";
    }
}
