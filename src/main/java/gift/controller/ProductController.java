package gift.controller;

import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private Long idCounter = 1L;

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        product.setId(idCounter);
        products.put(idCounter++,product);
        return product;
    }

}
