package gift.web;

import gift.web.dto.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("data", products.values());
        return "products";
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
