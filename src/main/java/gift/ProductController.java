package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<Long, Product>();


    @PostMapping ("/products")
    public void postMethod(@RequestBody RequestProduct requestProduct){
        Product product  = new Product(requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
        products.put(product.getId(), product);
    }

}
