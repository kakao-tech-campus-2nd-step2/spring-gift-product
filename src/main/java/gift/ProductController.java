package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> productMap = new HashMap<>();

    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productMap.values()
                .stream()
                .toList();
    }

    @PostMapping("/api/products")
    public String addProduct(@RequestBody Product newProduct) {
        if(productMap.containsKey(newProduct.id())) {
            return "이미 상품이 등록되어있습니다.";
        }
        productMap.put(newProduct.id(), newProduct);
        return "상품 등록이 완료되었습니다.";
    }
}
