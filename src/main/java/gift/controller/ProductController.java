package gift.controller;

import gift.model.Product;
import gift.model.ProductDao;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> products = productDao.findAll();
        return ResponseEntity.ok().body(products);
    }

    @Validated
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") @NotNull @Min(1) Long id) {
        Product product = productDao.findById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest request) {
        productDao.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") @NotNull @Min(1) Long id,
                                              @RequestBody ProductRequest request) {
        productDao.updateById(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") @NotNull @Min(1) Long id) {
        productDao.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
