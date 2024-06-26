package gift.controller;

import gift.vo.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private Long sequenceId = 1L;

    /**
     * 상품 조회 - 전체
     * @return 전체 상품 목록
     */
    @GetMapping()
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    /**
     * 상품 조회 - 한 개
     * @param id 조회할 상품의 ID
     * @return 조회한 product
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id) {
        Product product = products.get(id);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(product);
    }

    /**
     * 상품 추가
     * @param product 추가할 상품 (JSON 형식)
     * @return ResponseEntity로 Response 받음
     */
    @PostMapping("")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        synchronized (this) {
            product.setId(sequenceId++);
        }
        products.put(product.getId(), product);
        return ResponseEntity.ok(product);
    }

}
