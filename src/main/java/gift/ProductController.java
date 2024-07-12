package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1; // 상품 ID 생성을 위한 변수

    //상품 초기값 설정
    @PostConstruct
    private void init() {
        // 초기 상품 데이터 로드
        addProduct(new Product(nextId, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    // 모든 상품 조회
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
    }

    // 상품 추가
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return ResponseEntity.ok(product);
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (products.containsKey(id)) {
            product.setId(id);
            products.put(id, product);
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        products.remove(id);
        return ResponseEntity.ok().build();
    }
}