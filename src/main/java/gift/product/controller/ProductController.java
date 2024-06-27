package gift.product.controller;

import gift.product.model.ProductVo;
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
@RequestMapping("/product")
public class ProductController {

    private final Map<Long, ProductVo> products = new HashMap<>();

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductVo productVo, Model model) {
        products.put(productVo.getId(), productVo);
        model.addAttribute("productList", products.values());
        return "product";
    }

    @PostMapping("/modify")
    public String modifyProduct(@ModelAttribute ProductVo productVo, Model model) {
        products.put(productVo.getId(), productVo);
        model.addAttribute("productList", products.values());
        return "product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        products.remove(id);
        model.addAttribute("productList", products.values());
        return "product";
    }

    @GetMapping("/listup")
    public String listupProduct(Model model) {
        model.addAttribute("productList", products.values());
        return "product";  // Ensure this matches the name of your Thymeleaf template
    }
}
