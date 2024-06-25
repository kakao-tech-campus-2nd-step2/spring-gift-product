package gift;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    @PostConstruct
    public void init() {
        Product product1 = new Product();
        product1.setId(8146027);
        product1.setName("아이스 카페 아메리카노 T");
        product1.setPrice(4500);
        product1.setImageUrl("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        products.put(product1.getId(), product1);

    }

    @GetMapping
    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return product;
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (!products.containsKey(id)) {
            throw new NoSuchElementException("상품이 없습니다.");
        }
        product.setId(id);
        products.put(id, product);
        return product;
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }

}
