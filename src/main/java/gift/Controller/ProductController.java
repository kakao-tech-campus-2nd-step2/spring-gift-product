package gift.Controller;

import gift.Model.Product;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/api/products")
    public Product addProduct(
        @RequestParam(value = "id") long id,
        @RequestParam(value = "name") String name,
        @RequestParam(value = "price") long price,
        @RequestParam(value = "imageUrl") String imageUrl
    ) {
        Product newProduct = new Product(id, name, price, imageUrl);
        products.put(id, newProduct);
        return newProduct;
    }

    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable(value = "id") long id) {
        return products.get(id);
    }

    @GetMapping("/api/products/all")
    public List<Product> getAllProduct() {
        return products.values().stream().collect(Collectors.toList());
    }

    @DeleteMapping("/api/products/{id}")
    public Product deleteProduct(@PathVariable(value = "id") long id) {
        return products.remove(id);
    }

    @PutMapping("/api/products/{id}")
    public Product updateProduct(
        @PathVariable("id") long id,
        @RequestParam(value = "newId", required = false) Long newId,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "price", required = false) Long price,
        @RequestParam(value = "imageUrl", required = false) String imageUrl
    ) {
        Product existingProduct = products.get(id);

        long targetId = id;
        if (newId != null) {
            targetId = newId;
            products.remove(id);
        }
        String updatedName = existingProduct.name();
        if (name != null) {
            updatedName = name;
        }

        long updatedPrice = existingProduct.price();
        if (price != null) {
            updatedPrice = price;
        }

        String updatedImageUrl = existingProduct.imageUrl();
        if (imageUrl != null) {
            updatedImageUrl = imageUrl;
        }

        Product updatedProduct = new Product(targetId, updatedName, updatedPrice, updatedImageUrl);
        products.put(targetId, updatedProduct);
        return updatedProduct;
    }
}
