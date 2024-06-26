package gift;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    
    // 전체 상품 조회
    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return products.values();
    }
    
    //ID를 통한 물품 조회
    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return products.get(id);
    }



}
