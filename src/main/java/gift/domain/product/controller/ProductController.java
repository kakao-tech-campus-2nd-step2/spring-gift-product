package gift.domain.product.controller;

import gift.domain.product.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    public ProductController() {
        products.put(8146027L, new Product(8146027L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        // TODO: 상품 목록을 조회
        return new ArrayList<>(products.values());
    }

    @PostMapping("/api/products")
    public Product createProduct(Product product) {
        // TODO: 상품을 추가
        products.put(product.getId(), product);
        return product;
    }

    @PutMapping("/api/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        // TODO: 상품을 수정
        if (!products.containsKey(id)) {
            return null;  // 클라이언트 측에서 null 체크 필요
        }
        product.setId(id);
        products.put(id, product);
        return product;
    }
    @DeleteMapping("/api/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        // TODO: 상품을 삭제
        products.remove(id);
    }



}

