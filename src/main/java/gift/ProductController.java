package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;  // 새로운 상품이 추가될 때 사용할 다음 ID 값


    @GetMapping
    public String getProducts(Model model) {
        // products에 저장된 모든 상품
        model.addAttribute("products", new ArrayList<>(products.values()));
        return "productList";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        product.setId(nextId++);  // 상품의 id 설정
        products.put(product.getId(), product);

        return "redirect:/api/products";  // 새로운 상품 추가 후 상품 조회 화면으로 리다이렉트
    }

    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @GetMapping("/{id}/edit")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = products.get(id);

        if(product == null) {
            return "redirect:/api/products";
        }

        model.addAttribute("product", product);

        return "editProduct";
    }

    @PostMapping("/{id}")
    public String EditProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        // 상품 정보 수정
        product.setId(id);
        products.put(product.getId(), product);

        return "redirect:/api/products";
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        // 요청받은 id를 가진 상품을 삭제
        products.remove(id);

        return "redirect:/api/products";
    }

}
