package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products;

    // 생성자를 사용하여 products 초기화
    public ProductController() {
        products = new HashMap<>();
    }

    /**
     * 모든 상품 조회
     * @return 모든 상품 목록
     */
    @GetMapping
    public ResponseEntity<Map<Long, Product>> getProducts() {
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
