package gift.controller;

import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public Product getProduct(@RequestParam("id") Long id) {
        return products.get(id);
    }

}
