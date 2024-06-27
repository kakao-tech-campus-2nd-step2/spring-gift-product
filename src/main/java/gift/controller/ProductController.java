package gift.controller;

import gift.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();  // 상품을 저장하는 Map
    private Long currentId = 1L;  // 새로운 상품의 ID를 생성하기 위한 변수

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());  // 모든 상품을 리스트로 반환
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = products.get(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);  // 상품이 있으면 200 OK와 함께 반환
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 상품이 없으면 404 Not Found 반환
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = new Product(currentId++, product.name(), product.price(), product.imageUrl());
        products.put(newProduct.id(), newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);  // 생성된 상품을 201 Created와 함께 반환
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (products.containsKey(id)) {
            Product updatedProduct = new Product(id, product.name(), product.price(), product.imageUrl());
            products.put(id, updatedProduct);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 성공적으로 업데이트되면 204 No Content 반환
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 상품이 없으면 404 Not Found 반환
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (products.containsKey(id)) {
            products.remove(id);  // ID로 상품 삭제
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 성공적으로 삭제되면 204 No Content 반환
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 상품이 없으면 404 Not Found 반환
    }
}