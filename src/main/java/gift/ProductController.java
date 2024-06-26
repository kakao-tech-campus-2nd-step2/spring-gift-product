package gift;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;


@RestController
@RequestMapping("/api")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long sequence = 0L;

    // 전체 상품 조회
    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return products.values();
    }

    //ID를 통한 물품 조회
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(product);
    }

    // POST - 상품 추가
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestParam("id") Long id, @RequestParam("name") String name,
                                             @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl) {
        if (products.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 id입니다.");
        }
        Product product = new Product(id, name, price, imageUrl);
        products.put(++sequence, product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully.");
    }





}
