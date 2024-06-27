package gift.controller;

import gift.dto.ProductDTO;
import gift.service.ProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Collection<ProductDTO> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{sequence}")
    public ProductDTO getProduct(@PathVariable("sequence") Long sequence) {
        return productService.getProduct(sequence);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductDTO productDTO) {
        if (productService.addProduct(productDTO)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{sequence}")
    public ResponseEntity updateProduct(@PathVariable("sequence") long sequence, @RequestBody ProductDTO productDTO) {
        if (productService.updateProduct(sequence, productDTO)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{sequence}")
    public ResponseEntity deleteProduct(@PathVariable("sequence") long sequence) {
        if (productService.deleteProduct(sequence)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
