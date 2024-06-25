package gift.controller;

import gift.DTO.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public Collection<Product> findAll() {
        Product p1 = new Product(8146027L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        products.put(p1.getId(), p1);
        return products.values();
    }
}