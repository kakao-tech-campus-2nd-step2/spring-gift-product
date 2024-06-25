package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto request) {
        ProductResponseDto createdProduct = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /*
    @GetMapping("/api/products")
    public Product getProducts() {
    }

    @DeleteMapping("api/product/{id}")
    public void deleteProduct(@PathVariable int id) {

    }

    @PutMapping("api/product/{id}")
    public void editProduct(@PathVariable int id){

    }
     */
}
