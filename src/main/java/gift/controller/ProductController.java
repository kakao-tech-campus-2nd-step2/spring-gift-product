package gift.controller;

import gift.database.ProductDatabase;
import gift.model.Product;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductDatabase productDatabase;

    public ProductController(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }


    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productDatabase.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productDatabase.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDatabase.findById(id));
    }

    @GetMapping("/nameSearch")
    public ResponseEntity<Product> getProductByName(@RequestParam("name") String name) {
        Product product = productDatabase.getByName(name);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDatabase.getByName(name));
    }

    @PostMapping
    public ResponseEntity<Void> addProduct(@RequestBody Product product) {
        productDatabase.save(product);
        // 상태코드 201은 POST 나 PUT 으로 새로운 데이터를 서버에 생성하는 작업이 성공했을 때 반환한다고 함
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product oldProduct = productDatabase.findById(id);
        if (oldProduct == null) {
            return ResponseEntity.notFound().build();
        }
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setImageUrl(product.getImageUrl());
        productDatabase.save(oldProduct);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        Product product = productDatabase.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        productDatabase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
