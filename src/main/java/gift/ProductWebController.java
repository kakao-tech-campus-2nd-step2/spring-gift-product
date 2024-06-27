package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/web/products")
public class ProductWebController {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    // 초기 데이터 설정
    public ProductWebController() {
        products.put(8146027L, new Product(8146027L, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
        products.put(4565455L, new Product(4565455L, "아이스 카페 라테 T", 5500, "https://item.elandrs.com/upload/prd/orgimg/088/2005488088_0000001.jpg?w=750&h=&q=100"));
        products.put(1234567L, new Product(1234567L, "뜨거운 아이스 아메리카노 T", 6500, "https://dimg.donga.com/wps/NEWS/IMAGE/2017/02/06/82727038.1.jpg"));
    }

    // 상품 목록 페이지
    @GetMapping("/list")
    public String getAllProducts(Model model) {
        model.addAttribute("products", products.values());
        return "productList";
    }

    // 상품 상세 페이지
    @GetMapping("/detail/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
        Product product = products.get(id);
        if (product == null) {
            return "404"; // Product not found page
        }
        model.addAttribute("product", product);
        return "productDetail";
    }


    // 상품 추가 폼 페이지
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    // 상품 추가 처리
    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        product.setId(nextId++);
        products.put(product.getId(), product);
        return "redirect:/web/products/list";
    }

    // 상품 수정 폼 페이지
    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = products.get(id);
        if (product == null) {
            return "404"; // Product not found page
        }
        model.addAttribute("product", product);
        return "editProduct";
    }

    // 상품 수정 처리
    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        if (!products.containsKey(id)) {
            return "404"; // Product not found page
        }
        product.setId(id);
        products.put(id, product);
        return "redirect:/web/products/list";
    }

    // 상품 삭제 처리
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        if (!products.containsKey(id)) {
            return "404"; // Product not found page
        }
        products.remove(id);
        return "redirect:/web/products/list";
    }


}
