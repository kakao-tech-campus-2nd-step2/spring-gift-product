package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<String, Object> products = new HashMap<>();

    @GetMapping("/api/products")
    public Map<String, Object> produce() {
        products.put("id", 8146027);
        products.put("name", "아이스 카페 아메리카노 T");
        products.put("price", 4500);
        products.put("imageUrl", "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        return products;
    }
}
