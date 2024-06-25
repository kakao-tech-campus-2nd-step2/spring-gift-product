package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    //상품 반환
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return products.get(id); // 예시 ID를 사용하여 제품을 검색
    }
    //상품 추가
    @PostMapping
    public void addProduct(@RequestBody Product product) {

        products.put(product.getId(), product);
    }
    //상품 수정
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {

        product.setId(id); // URL의 ID로 제품 ID 설정
        products.put(id, product);
    }
    //상품 삭제
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {

        products.remove(id);
    }

}