package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    @GetMapping
    public Collection<Product> getProducts() {
        return products.values();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Product>

}
