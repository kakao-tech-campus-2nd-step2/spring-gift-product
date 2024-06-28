package gift.controller;

import gift.model.Product;
import gift.model.ProductForm;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // 모든 제품을 가져오는 엔드포인트
    @GetMapping("")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    // 특정 ID의 제품을 가져오는 엔드포인트
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product result = repository.findById(id).orElse(null); // Optional 처리
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // 새로운 제품을 생성하는 엔드포인트
    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<Product> postProduct(@RequestBody ProductForm form) {
        Product product = new Product(form); // ProductForm을 Product로 변환
        Product result = repository.save(product);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // 상태 코드를 BAD_REQUEST로 수정
    }

    // 특정 ID의 제품을 수정하는 엔드포인트
    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Product> putProduct(@RequestBody ProductForm form, @PathVariable Long id) {
        Product product = new Product(form); // ProductForm을 Product로 변환
        Product result = repository.edit(id, product);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // 특정 ID의 제품을 삭제하는 엔드포인트
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean result = repository.delete(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found"); // 메시지를 명확히 수정
    }
}
