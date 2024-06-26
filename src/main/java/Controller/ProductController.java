package Controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/{id:[1-9][0-9}*}")
    public Product getProduct(@PathVariable Long id){
        if(!products.containsKey(id)){
            System.out.println("{%d}에 매칭되는 Product가 없습니다.".formatted(id));
            return null;
        }
        return products.get(id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product product){
        if(!products.containsKey(product.getId())){
            products.put(product.getId(), product);
            return;
        }
        System.out.println("{%d}에 매칭되는 Product가 이미 존재합니다.".formatted(product.getId()));
    }

    @PutMapping("/{id:[1-9][0-9]*}")
    public void updateProduct(@RequestBody Product product, @PathVariable Long id){
        if(products.containsKey(product.getId())){
            products.put(product.getId(), product);
            return;
        }
        System.out.println("{%d}에 매칭되는 Product가 이미 존재하지 않습니다.");
    }
}
