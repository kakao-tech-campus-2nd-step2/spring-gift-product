package gift.controller;

import gift.dto.ProductDto;
import gift.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductDto getProduct(@PathVariable("id") Long id){
        return productService.findProductById(id);
    }

    @GetMapping()
    public List<ProductDto> getAll(){
        return productService.findAllProducts();
    }

    @PostMapping()
    public ResponseEntity<Map<String, Long>> add(@RequestBody ProductDto productDto){
        Long savedId = productService.addProduct(productDto);
        Map<String, Long> response = new HashMap<>();
        response.put("id", savedId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable("id") Long id,
                             @RequestParam("price") int price){
        return productService.updateProduct(id, price);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Long>> delete(@PathVariable("id") Long id){
        Long deletedId = productService.deleteProduct(id);
        Map<String, Long> response = new HashMap<>();
        response.put("id", deletedId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
