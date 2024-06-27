package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<Long, Product>();

    @GetMapping("/products") //조회
    public List<Product> getMethod() {
        List<Product> list = new LinkedList<Product>();
        Iterator<Long> keys = products.keySet().iterator();
        while (keys.hasNext()) {
            Long key = keys.next();
            Product data = products.get(key);
            list.add(data);
        }
        return list;
    }

    @GetMapping("/products/{id}") //조회
    public Product single_getMethod(@PathVariable("id") Long id) {
        Product data = products.get(id);

        return data;
    }

    @PostMapping ("/products")
    public void postMethod(@RequestBody RequestProduct requestProduct){
        Product product  = new Product(requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
        products.put(product.getId(), product);
    }

}
