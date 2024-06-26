package gift.controller;

import gift.dto.ProductResponseDto;
import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private static Long sequence = 0L;

    @GetMapping("/api/products")
    public ResponseEntity<List<ProductResponseDto>> getAllProducts() {

        List<Product> productsList = products.values().stream().toList();
        if(productsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ProductResponseDto> dtoList = productsList.stream()
            .map(ProductResponseDto::new)
            .toList();
        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {

        Product product = products.get(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductResponseDto productResponseDto = new ProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PostMapping("/api/products")
    public void addProduct(@RequestBody ProductResponseDto productResponseDto) {
        Long id = ++sequence;
        Product product = new Product(id, productResponseDto);
        products.put(id, product);
    }

    @PutMapping("/api/products")
    public void updateProduct(@RequestBody ProductResponseDto productResponseDto) {
        products.put(productResponseDto.id(), new Product(productResponseDto));
    }

    @DeleteMapping("/api/products")
    public void deleteProduct(@RequestParam("id") Long id) {
        products.remove(id);
    }

}
