package gift.controller;
import gift.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // GET(one or more), POST(one), DELETE(one), PUT[UPDATE](one) 순으로 작성

    private final Map<Long, Product> products = new HashMap<>();
    private Long sequenceId = 1L;


    //GET : 한 개의 상품 조회 null은 없다고 가정.
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(value = "id") Long id) {
        Product product = products.get(id);
        return ResponseEntity.ok(product);
    }

    //GET : 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(new ArrayList<>(products.values()));
    }

    // POST : 새로운 상품 추가
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        product.setId(sequenceId++);
        products.put(product.getId(), product);
        return ResponseEntity.ok(product);
    }

    // DELETE : 특정 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Long id) {
        products.remove(id);
        return ResponseEntity.noContent().build();
    }

    // PUT : 특정 상품 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long id, @RequestBody Product updatedProduct) {
        Product product = products.get(id);
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setImageUrl(updatedProduct.getImageUrl());
        products.put(id, product);
        return ResponseEntity.ok(product);
    }



}
