package gift.controller;

import gift.model.Product;
import gift.model.ProductForm;
import gift.repository.MemoryProductRepository;
import java.util.List;
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

    private final MemoryProductRepository repository;

    public ProductController(MemoryProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping(path = "", consumes = "application/json")
    public Product postProduct(@RequestBody ProductForm form) {
        return repository.save(form);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public Product putProduct(@RequestBody ProductForm form, @PathVariable Long id) {
        return repository.edit(id, form);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean result = repository.delete(id);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }
}
