package gift.controller;

import gift.domain.Product;
import gift.dto.CreateProductDto;
import gift.dto.UpdateProductDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 추가
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto productDto) {
        try{
            Product product = productService.createProduct(productDto);
            return ResponseEntity.ok(product);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        if (allProducts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.ok(allProducts);
    }


    // 특정 상품 조회
    @GetMapping("/{product_id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long product_id) {
        try{
            Product product = productService.getProduct(product_id);
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 상품 정보 update
    @PutMapping("/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long product_id, @RequestBody UpdateProductDto productDto) {
        try {
            Product updatedProduct = productService.updateProduct(product_id, productDto);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // 상품 정보 삭제
    @DeleteMapping("/{product_id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long product_id) {
        try{
            productService.deleteProduct(product_id);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
