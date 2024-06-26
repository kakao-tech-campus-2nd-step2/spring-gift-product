package gift.controller;

import gift.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 상품 관련 요청을 처리하는 컨트롤러 클래스
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    /**
     * 새 상품을 생성하고 맵에 저장함
     *
     * @param product 저장할 상품 객체
     * @return 저장된 상품 객체 반환
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        products.put(product.id(), product);
        return product;
    }

    /**
     * 주어진 ID에 해당하는 상품을 반환함
     *
     * @param id 조회할 상품의 ID
     * @return ID에 해당하는 상품 객체 반환
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id);
        }
        return product;
    }

    /**
     * 모든 상품을 반환함
     *
     * @return 모든 상품이 포함된 리스트 반환
     */
    @GetMapping("/all")
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    /**
     * 주어진 ID에 해당하는 상품을 삭제함
     *
     * @param id 삭제할 상품의 ID
     * @return 응답 엔티티
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id);
        }
        products.remove(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * 주어진 ID에 해당하는 상품을 갱신함
     *
     * @param id 갱신할 상품의 ID
     * @param product 갱신할 상품 객체
     * @return 갱신된 상품 객체 반환
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!products.containsKey(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id " + id);
        }
        products.put(id, product);
        return product;
    }
}
