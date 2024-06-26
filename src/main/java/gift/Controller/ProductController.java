package gift.Controller;

import gift.Model.Product;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping
    public Product addProduct(@RequestBody Product newProduct) {
        products.put(newProduct.id(), newProduct);
        return newProduct;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(value = "id") long id) {
        return products.get(id);
    }

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        return new ArrayList<>(products.values());
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable(value = "id") long id) {
        return products.remove(id);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") long id, @RequestBody Product updatedProduct) {
        products.remove(id);
        products.put(updatedProduct.id(), updatedProduct);
        return updatedProduct;
    }
}
