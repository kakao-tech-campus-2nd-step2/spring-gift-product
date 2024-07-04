package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createProduct(@RequestBody Product product) {
        boolean success = productService.createProduct(product);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("message", "Product created successfully.");
            response.put("product", product);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        response.put("message", "Failed to create product.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        boolean success = productService.updateProduct(id, product);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            Product updatedProduct = productService.getProductById(id);
            response.put("message", "Product updated successfully.");
            response.put("product", updatedProduct);
            return ResponseEntity.ok(response);
        }
        response.put("message", "Failed to update product.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Map<String, Object>> patchProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        boolean success = productService.patchProduct(id, updates);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            Product updatedProduct = productService.getProductById(id);
            response.put("message", "Product patched successfully.");
            response.put("product", updatedProduct);
            return ResponseEntity.ok(response);
        }
        response.put("message", "Failed to patch product.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @PatchMapping
    public ResponseEntity<Map<String, Object>> patchProducts(@RequestBody List<Map<String, Object>> updatesList) {
        List<Product> updatedProducts = productService.patchProducts(updatesList);
        Map<String, Object> response = new HashMap<>();
        int originalCount = updatesList.size();
        int updateCount = updatedProducts.size();

        response.put("updatedProducts", updatedProducts);

        if (updateCount == originalCount) {
            response.put("message", "All products patched successfully.");
            return ResponseEntity.ok(response);
        }

        if (updateCount > 0) {
            response.put("message", "Some products patched successfully.");
            return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).body(response);
        }

        response.put("message", "No products patched.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable Long id) {
        boolean success = productService.deleteProduct(id);
        Map<String, Object> response = new HashMap<>();
        if (success) {
            response.put("message", "Product deleted successfully.");
            return ResponseEntity.noContent().build();
        }
        response.put("message", "Failed to delete product.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
