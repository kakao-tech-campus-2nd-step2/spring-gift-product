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

    //ID를 통한 물품 조회 - product를 return하면 JSON이 된다.
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        for (Product product : products.values()) {
            if(product.id() == id){
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // POST - 상품 추가
    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestParam("product") Product addproduct) {

        long id = addproduct.id();
        for (Product product : products.values()) {
            if(product.id() == id){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 id입니다.");
            }
        }
        products.put(++sequence, addproduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully.");
    }


    // DELETE - 상품 삭제
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        for (Product product : products.values()) {
            if(product.id() == id){
                products.values().remove(product);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully.");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 id입니다.");
    }

}
