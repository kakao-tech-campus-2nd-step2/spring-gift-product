package gift.web;

import gift.web.dto.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        // 여기에는 products 전체 상품을 리턴
        return new ResponseEntity<>(List.copyOf(products.values()), HttpStatus.OK);
    }

    // products/{상품번호}의 GetMapping
    @GetMapping("/{id}") //Query Param과 Path Variable 사용의 차이점 알아보기
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        if(products.containsKey(id)) return new ResponseEntity<>(products.get(id), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) { //RequestBody와 RequestParam의 차이점 알아보기
        products.put(product.id(), product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    // PUT은 업데이트시 존재하지 않는다면, 생성을 하게 되는데, POST와의 차이점?이 뭔 지 알아보기
    // PUT 구현
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        boolean exists = products.containsKey(id);
        products.put(id, product);

        if(exists) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if(products.containsKey(id)) {
            products.remove(id);
            return ResponseEntity.ok("Delete Success");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Not Found");
    }
}
