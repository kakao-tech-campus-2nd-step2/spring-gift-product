package gift.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gift.model.Product;
import gift.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
        Product product = productService.getProductById(id);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(product);
    }
    
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if(updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(updatedProduct);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") long id) {
        if(!productService.deleteProduct(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
