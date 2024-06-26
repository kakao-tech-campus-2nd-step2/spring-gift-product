package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    // 모든 상품을 보여주는 페이지
    @GetMapping
    public String AllProducts(Model model) {
        model.addAttribute("products", products.values());
        return "Products"; // products.html Thymeleaf 템플릿으로 렌더링
    }

    // 상품 추가 폼 페이지
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "Add_product"; // Add_product.html Thymeleaf 템플릿으로 렌더링
    }

    // 실제 상품 추가 처리
    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        products.put(product.getId(), product);
        return "redirect:/admin/products"; // 상품 목록 페이지로 바로가기
    }

    // 상품 수정 폼 페이지
    @GetMapping("/edit/{id}")
    public String EditProductForm(@PathVariable Long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        return "Edit_product"; // edit_product.html Thymeleaf 템플릿으로 렌더링
    }

    // 실제 상품 수정 처리
    @PostMapping("/update/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        product.setId(id);
        products.put(id, product);
        return "redirect:/admin/products"; // 상품 목록 페이지로 바로가기
    }

    // 상품 삭제 처리
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        products.remove(id);
        return "redirect:/admin/products"; // 상품 목록 페이지로 바로가기
    }
}