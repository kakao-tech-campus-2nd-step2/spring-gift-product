package gift.Controller;

import gift.Repository.JdbcProducts;
import gift.Repository.Products;
import gift.domain.Product;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jdbc/products")
public class JdbcProductController {
    private final JdbcProducts jdbcProducts;
    
    //생성자 주입 권장
    public JdbcProductController(JdbcProducts jdbcProducts) {
        this.jdbcProducts = jdbcProducts;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(jdbcProducts.findAll());
    }

    @PostMapping
    public ResponseEntity<String> addProducts(@RequestBody Product product) {
        if (jdbcProducts.addProduct(product)) {
            return new ResponseEntity<>("상품 추가 완료", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("이미존재하는 상품 id", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> modifyProducts(@PathVariable("id") long id, @RequestBody Product product) {
        if (jdbcProducts.updateProduct(product)) {
            return new ResponseEntity<>("상품 수정 완료", HttpStatus.OK);
        }
        return new ResponseEntity<>("존재하지 않는 상품 id", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable("id") long id) {
        if (jdbcProducts.deleteProduct(id)) {
            return new ResponseEntity<>("상품삭제 완료", HttpStatus.OK);
        }
        return new ResponseEntity<>("없는 상품", HttpStatus.BAD_REQUEST);
    }





}
