package gift.controller;

import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> addProduct(@RequestBody ProductRequestDto request) {
        ProductResponseDto createdProduct = productService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id){
        try{
            return productService.findById(id);
        } catch (IllegalArgumentException e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping
    public List<ProductResponseDto> getProducts() {
        return productService.findAll();
    }

    /*
    @DeleteMapping("api/product/{id}")
    public void deleteProduct(@PathVariable int id) {

    }

    @PutMapping("api/product/{id}")
    public void editProduct(@PathVariable int id){

    }
     */
}
