package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    /**
     * id로 특정 상품 조회
     * @param id 조회할 상품의 id
     * @return 조회된 상품 객체
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.get(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK); // 200 OK
        }
        return ResponseEntity.notFound().build();

    }

    /**
     * 새로운 상품 추가
     * @param product 추가할 상품
     * @return 같은 ID의 상품이 존재하지 않으면 201 Created, 아니면 400 Bad Request
     */
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        if (products.containsKey(product.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400 Bad Request
        }
        products.put(product.getId(), product);
        return new ResponseEntity<>(product, HttpStatus.CREATED); // 201 Created
    }


}
