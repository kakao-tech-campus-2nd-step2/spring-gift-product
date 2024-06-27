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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final Map<Long, ProductVo> products = new HashMap<>();

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductVo productVo, Model model) {
        products.put(productVo.getId(), productVo);

        model.addAttribute("productList", products.values());
        return "product-list";
    }

    @PostMapping("/modify/{id}")
    public String modifyProduct(@PathVariable Long id,
        @RequestParam("name") String name,
        @RequestParam("price") int price,
        @RequestParam("imageUrl") String imageUrl,
        Model model) {
        ProductVo product = products.get(id);
        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imageUrl);
        products.put(id, product);

        model.addAttribute("productList", products.values());
        return "product-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        products.remove(id);
        model.addAttribute("productList", products.values());
        return "product-list";
    }

    @GetMapping("/listup")
    public String listup(Model model) {
        model.addAttribute("productList", products.values());
        return "product-list";  // Ensure this matches the name of your Thymeleaf template
    }

    @GetMapping
    public String listAllProducts(Model model) {
        model.addAttribute("productList", products.values());
        return "product-list";
    }
}
