package gift.controller;

import gift.dto.ProductResponseDto;
import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public ResponseEntity<ProductResponseDto> getProduct(@RequestParam("id") Long id) {

        Product product = products.get(id);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ProductResponseDto productResponseDto = new ProductResponseDto(product);
        return new ResponseEntity<>(productResponseDto, HttpStatus.OK);
    }

    @PostMapping("/api/products")
    public void addProduct(@RequestBody ProductResponseDto productResponseDto) {
        products.put(1L, new Product(productResponseDto));
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
