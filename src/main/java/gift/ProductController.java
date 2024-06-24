package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    //Create
    @PostMapping("api/products")
    public void addProduct(@RequestBody Product product) {
        products.put(product.getId(), product);
    }
    //Read
    @GetMapping("api/products")
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(entry.getValue());
        }

        return productList;
    }
    //Update
    @PutMapping("api/products/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        products.put(id, product);
    }
    //Delete
    @DeleteMapping("api/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        products.remove(id);
    }

}
