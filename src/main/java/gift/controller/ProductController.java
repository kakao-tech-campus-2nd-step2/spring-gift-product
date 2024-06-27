package controller;

import model.Product;
import service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return productService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return ResponseEntity.status(201).body(productService.save(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    if (!productService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    product.setId(id);
    return ResponseEntity.ok(productService.save(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (!productService.findById(id).isPresent()) {
      return ResponseEntity.notFound().build();
    }
    productService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
