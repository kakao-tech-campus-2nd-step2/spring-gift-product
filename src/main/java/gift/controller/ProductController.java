package gift.controller;

import gift.domain.Product;
import java.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1;

    @GetMapping
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product product = products.get(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> postProduct(@RequestBody Product product) {
        if (products.containsKey(product.id())) {
            return new ResponseEntity<>("해당 ID의 상품이 이미 존재합니다.",HttpStatus.CONFLICT);
        }
        product = new Product(currentId++, product.name(), product.price(), product.imageUrl());
        products.put(product.id(), product);
        return new ResponseEntity<>("상품이 정상적으로 추가되었습니다.", HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putProduct(@RequestBody Product product, @PathVariable Long id) {
        if (!products.containsKey(id)) {
            return new ResponseEntity<>("해당 ID의 상품이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
        product = new Product(id, product.name(), product.price(), product.imageUrl());
        products.put(id, product);
        return new ResponseEntity<>("상품 정보가 정상적으로 변경되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            return new ResponseEntity<>("해당 ID의 상품이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }
        products.remove(id);
        return new ResponseEntity<>("상품이 정상적으로 삭제되었습니다", HttpStatus.NO_CONTENT);
    }
}
