package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;  // 새로운 상품이 추가될 때 사용할 다음 ID 값


    @GetMapping
    public String getProducts(Model model) {
        // products에 저장된 모든 상품을 반환
        model.addAttribute("products", new ArrayList<>(products.values()));
        return "productList";
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {  // response body에 있는 JSON 데이터를 Product 객체로 변환
        product.setId(nextId++);  // 상품의 id 설정
        products.put(product.getId(), product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        // 요청받은 id를 가진 상품이 존재하지 않는 경우
        if(!products.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 상품 정보 수정
        product.setId(id);
        products.put(product.getId(), product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        // 요청받은 id를 가진 상품이 존재하지 않는 경우
        if(!products.containsKey(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // 요청받은 id를 가진 상품을 삭제
        products.remove(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
