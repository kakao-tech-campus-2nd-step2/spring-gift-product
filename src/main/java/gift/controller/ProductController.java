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
        List<Product> products = repository.findAll();
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Product result = repository.findById(id);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    
    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<Product> postProduct(@RequestBody ProductForm form) {
        Product result = repository.save(form);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @PutMapping(path = "/{id}", consumes = "application/json")
    public ResponseEntity<Product> putProduct(@RequestBody ProductForm form,
        @PathVariable Long id) {
        Product result = repository.edit(id, form);
        if (result != null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



 // 수정 필요
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean result = repository.delete(id);
        if (result) {
              return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
        }
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Bad request");
    }

}