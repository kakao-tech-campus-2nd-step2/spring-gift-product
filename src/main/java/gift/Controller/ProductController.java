package gift;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    // DB를 대체할 HashMap
    private final Map<Long, Product> products = new HashMap<>();

    // HashMap에 입력받은 
    @PostMapping("/products")
    public String addProduct(@RequestBody Product product){
        products.put(product.id(), product);

        if(products.get(product.id()) == product) return "추가 성공.";
        return "추가 실패.";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = products.remove(id);

        if (product != null) return "제거 성공";
        return "제거 실패";
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@RequestBody Product product){
        products.put(product.id(), product);

        if(products.get(product.id()) == product) return "업데이트 성공.";
        return "업데이트 실패.";
    }

    @GetMapping("/products")
    public Collection<Product> viewAllProducts(){
        return products.values();
    }

    @GetMapping("/products/{id}")
    public Product viewProduct(@PathVariable Long id){
        return products.get(id);
    }
}
