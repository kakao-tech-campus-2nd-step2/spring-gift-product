package gift;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping
    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable(value = "id") long id) {
        return products.get(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product newProduct){
        products.put(newProduct.id(), newProduct);
        return newProduct;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(value = "id") long id, @RequestBody Product updatedProduct){
        products.put(id, updatedProduct);
        return updatedProduct;
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable(value = "id") long id){
        return products.remove(id);
    }

}
