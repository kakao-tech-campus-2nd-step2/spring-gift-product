package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    //Create
    @PostMapping("api/products")
    public void addProduct(@RequestBody Product product) {
        products.put(product.id, product);
    }




}
