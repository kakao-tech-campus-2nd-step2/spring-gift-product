package gift.controller;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private Long idCounter = 1L;

    //상품 추가 기능
    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        product.setId(idCounter);
        products.put(idCounter++,product);
        return product;
    }

    //상품 전체 조회 기능
    @GetMapping
    public List<Product> selectAllProduct() {
        return new ArrayList<>(products.values());
    }

}
