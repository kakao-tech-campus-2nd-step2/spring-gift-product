package gift.Controller;
import gift.Model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ProductController {
    // DB를 대체할 HashMap
    private final Map<Long, Product> products = new HashMap<>();

    // HashMap에 입력받은 데이터 추가, json형태로 전달 명시
    @PostMapping("/products")
    @ResponseBody
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        products.put(product.id(), product);

        if(products.get(product.id()) == product) return ResponseEntity.status(HttpStatus.CREATED).body("추가 성공");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("추가 실패");
    }

    // HashMap에 있는 key에 해당하는 값을 제거
    @DeleteMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        Product product = products.remove(id);

        if (product != null) return ResponseEntity.status(HttpStatus.OK).body("제거 성공");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("제거 실패");
    }

    // key값이 id인 데이터 업데이트
    @PutMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product){
        if (!products.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("업데이트 실패");
        }
        products.put(id, product);
        return ResponseEntity.status(HttpStatus.OK).body("업데이트 성공");
    }

    // HashMap에 있는 모든 데이터 불러오기, products.html 사용
    @GetMapping("/products")
    public String viewAllProducts(Model model){
        model.addAttribute("products", products.values());
        return "products";
    }

    // key값이 id인 데이터 불러오기, products.html 사용
    @GetMapping("/products/{id}")
    public String viewProduct(@PathVariable Long id, Model model){
        model.addAttribute("products", products.get(id));
        return "products";
    }
}
