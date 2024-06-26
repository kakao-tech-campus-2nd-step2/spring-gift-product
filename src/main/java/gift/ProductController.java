package gift;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController // 해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냄
@RequestMapping("/api/products") // 이 클래스의 모든 HTTP 요청은 ("/api/products")로 매핑됨
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    // 모든 상품을 조회하여 'ResponseEntity'에 담아 반환
    public ResponseEntity<Collection<ProductModel>> 모든상품조회() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    // 주어진 id에 해당하는 상품을 조회하여 'ResponseEntity'에 담아 반환
    public ResponseEntity<ProductModel> 상품조회(@PathVariable("productId") long id) {
        ProductModel product = productService.getProductById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping
    // 전달된 JSON 형식의 상품 정보를 받아서 서비스를 통해 상품을 추가
    public ResponseEntity<ProductModel> 상품추가(@RequestBody ProductModel product) {
        ProductModel addedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductModel> 상품수정(@PathVariable("productId") long id, @RequestBody ProductModel updatedProduct) {
        ProductModel product = productService.updateProduct(id, updatedProduct);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> 상품삭제(@PathVariable("productId") long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}