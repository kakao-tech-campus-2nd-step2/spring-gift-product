package gift.Controller;
import gift.Model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    // DB를 대체할 HashMap
    private final Map<Long, Product> products = new HashMap<>();

    // HashMap에 입력받은 데이터 추가
    @PostMapping("/products")
    public String addProduct(@RequestBody Product product){
        products.put(product.id(), product);

        if(products.get(product.id()) == product) return "추가 성공.";
        return "추가 실패.";
    }

    // HashMap에 있는 key에 해당하는 값을 제거
    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id){
        Product product = products.remove(id);

        if (product != null) return "제거 성공";
        return "제거 실패";
    }
}
