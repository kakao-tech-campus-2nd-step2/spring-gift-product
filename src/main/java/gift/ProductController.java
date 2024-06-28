package gift;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private long sequence = 0L;

    ProductController(){
        products.put(++sequence, new Product(3, "name" , 14000, "url"));
    }

    @GetMapping("/products")
    public Collection<Product> getProducts() {
        return products.values();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        for (Product product : products.values()) {
            if (product.id() == id) {
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody Product addProduct) {
        long id = addProduct.id();
        for (Product product : products.values()) {
            if (product.id() == id) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("중복된 id입니다.");
            }
        }
        products.put(++sequence, addProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully.");
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        for (Product product : products.values()) {
            if (product.id() == id) {
                products.values().remove(product);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Product deleted successfully.");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 id입니다.");
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updateProduct) {
        for (Product product : products.values()) {
            if (product.id() == id) {
                products.values().remove(product);
                products.put(++sequence, updateProduct);
                return ResponseEntity.status(HttpStatus.CREATED).body("Product updated successfully.");
            }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("존재하지 않는 id입니다.");
    }



}
