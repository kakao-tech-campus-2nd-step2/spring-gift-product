package gift.controller;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        products.put(idCounter++, product);
        return product;
    }

    //상품 전체 조회 기능
    @GetMapping
    public List<Product> selectAllProduct() {
        return new ArrayList<>(products.values());
    }

    //상품 단일 조회 기능
    @GetMapping("/{id}")
    public Product selectProductById(@PathVariable Long id) {
        return products.get(id);
    }

    //상품 삭제 기능
    @DeleteMapping("/{id}")
    public Long deleteProduct(@PathVariable Long id) {
        Product existingProduct = products.get(id);
        if (existingProduct != null) {
            products.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("No Exists Product By Id");
        }
    }
}
