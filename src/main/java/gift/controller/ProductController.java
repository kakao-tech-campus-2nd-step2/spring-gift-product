package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
    // Service와 의존성 추가
    @Autowired
    private ProductService productService;

    // 상품 추가(Create)
    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String response = productService.productPost(product);
        if (response.equals("상품 생성")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // 단일 상품 조회(Read)
    @GetMapping("/{id}")
    public ResponseEntity<Product> selectProduct(@PathVariable Long id) {
        Product product = productService.productGET(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 전체 상품 조회(Read)
    @GetMapping
    public ResponseEntity<Collection<Product>> selectAllProducts() {
        return new ResponseEntity<>(productService.productGET(), HttpStatus.OK);
    }

    // 상품 수정(Update)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        String response = productService.productPut(id, product);
        if (response.equals("상품 수정")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // 상품 삭제(Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        String response = productService.productDelete(id);
        if (response.equals("상품 삭제")) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
