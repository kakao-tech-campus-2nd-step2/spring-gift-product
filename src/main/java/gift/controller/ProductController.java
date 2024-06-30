package gift.controller;

import gift.model.ProductDAO;
import gift.model.ProductDTO;
import gift.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<ProductDAO> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDAO> getProduct(@PathVariable Long id) {
        ProductDAO result = repository.findById(id);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<ProductDAO> postProduct(@RequestBody ProductDTO form) {
        ProductDAO result = repository.save(form);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<ProductDAO> putProduct(@RequestBody ProductDTO form,
                                                 @PathVariable Long id) {
        ProductDAO result = repository.edit(id, form);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean result = repository.delete(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bad request");
    }
}
