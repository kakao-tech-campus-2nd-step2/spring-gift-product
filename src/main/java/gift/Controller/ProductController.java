package gift.Controller;

import gift.Model.Product;
import java.util.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
