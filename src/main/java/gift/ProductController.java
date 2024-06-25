package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    @GetMapping
    public List<Product> getProduct(){
        return new ArrayList<>(products.values());
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product){
        products.put(nextId++,product);
        return product;
    }

    @PutMapping
    public Product changeProduct(@PathVariable Long id, @RequestBody Product product){
        if(!products.containsValue(id)){
            throw new RuntimeException("id에 해당하는 객체가 없습니다");
        }
        product.id = id;
        products.put(id, product);
        return product;
    }
}
