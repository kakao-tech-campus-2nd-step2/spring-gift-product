package gift;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/products")
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
    public String getProducts(Model model) {
        model.addAttribute("products", new ArrayList<>(products.values()));
        model.addAttribute("product", new Product()); // Add empty product object for the form
        return "product-list";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("상품이 없습니다.");
        }
        product.setId(id);
        products.put(id, product);
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("상품이 없습니다.");
        }
        products.remove(id);
    }

    @GetMapping("/{id}")
    public Object getProductById(@PathVariable("id") Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("상품이 없습니다.");
        }
        return products.get(id);
    }


}
