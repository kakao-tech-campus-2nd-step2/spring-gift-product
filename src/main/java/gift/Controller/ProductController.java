package gift.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gift.Model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/")
    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return products.get(id);
    }

    @PostMapping()
    public void addProduct(@RequestBody Product product){
        if(products.containsKey(product.id())){
            System.out.println("이미 Product가 존재합니다.");
            return;
        }
        products.put(product.id(), product);
        System.out.println("추가됨");
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable Long id){
        if(!products.containsKey(product.id())){
            System.out.println("{%d}에 매칭되는 Product가 이미 존재하지 않습니다.".formatted(id));
            return;
        }
        products.put(product.id(), product);
        System.out.println("수정됨");
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        if(products.containsKey(id)){
            System.out.println("{%d}에 매칭되는 Product가 이미 존재하지 않습니다.".formatted(id));
            return;
        }
        products.remove(id);
        System.out.println("삭제됨");
    }

}
