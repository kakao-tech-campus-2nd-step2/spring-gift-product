package gift.Controller;

import gift.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    long id = 0L;

    //모든 상품 반환
    @GetMapping("/getAllProducts")
    public Map<Long, Product> getProductsController(){
        return this.products;
    }

    //id 상품 하나 반환
    @GetMapping("/getProduct/{id}")
    public Product getProductByIdController(@PathVariable Long id){
        return products.get(id);
    }

    //상품 추가
    @PostMapping("/postProduct")
    public void postProductController(@RequestBody Product product){
        id++;
        product.setId(id);
        products.put(id, product);
    }

    //상품 삭제
    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProductController(@PathVariable Long id){
        products.remove(id);
    }
}
