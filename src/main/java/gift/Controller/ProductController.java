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
}
