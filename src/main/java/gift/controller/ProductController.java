package gift.controller;

import gift.vo.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    /**
     * 상품 조회 - 전체
     * @return 전체 상품 목록
     */
    @GetMapping()
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
