package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
        //products.values()로 products 맵에 포함된 모든 값을 collection으로 반환
        //하여 ArrayList로 바꾼 뒤에 http요청에 대한 성공 응답의 본문을 객체->제이슨으로 구성한다.
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        // @RequestBody는 클라이언트 요청(Post)의 본문에서 product객체를 추출하여,
        // 메서드의 매개변수로 전달하는 어노테이션이다.
        products.put(product.id(), product);
        return ResponseEntity.status(201).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!products.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        products.put(id, product);
        return ResponseEntity.ok(product);
    }

}
