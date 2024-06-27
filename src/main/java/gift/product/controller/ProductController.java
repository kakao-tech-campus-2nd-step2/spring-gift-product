package gift.product.controller;

import gift.product.dto.ProductDTO;
import gift.product.model.Product;
import gift.product.service.ProductService;
import java.util.List;
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
@RequestMapping("/api/products")
public class ProductController {

    ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    @PostMapping("/insert")
    public ResponseEntity<Product> insertProduct(@RequestBody ProductDTO productDTO) {
        Product responseProduct = productService.insertProduct(productDTO);

        return ResponseEntity.ok(responseProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProductAll() {
        List<Product> productAll = productService.getProductAll();
        return ResponseEntity.ok(productAll);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable(name = "id") Long id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") Long id,
        @RequestBody ProductDTO productDTO) {
        Product product = productService.updateProduct(id, productDTO);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable(name = "id") Long id) {
        productService.deleteProduct(id);

        return ResponseEntity.ok().build();
    }
}