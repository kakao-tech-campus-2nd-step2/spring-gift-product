package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
public class PdController {
    private final Map<Long, Product> products = new HashMap<>();
    long currentId = 1;

    @GetMapping("/products")
    public Map<Long, Product> getAllProducts(){
        return products;
    }

    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id) {
        return products.get(id);
    }

    @PostMapping("/products")
    public void putProduct(@RequestBody Product product){
        long id = currentId++;
        Product newProduct = new Product(id, product.name(), product.price(), product.imageUrl());
        products.put(id, newProduct);
    }
    @PutMapping("/products/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (products.containsKey(id)) {
            products.put(id, product);
        }
    }
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }
}
