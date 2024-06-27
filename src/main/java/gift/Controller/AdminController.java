package gift.Controller;

import gift.Model.Product;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", new ArrayList<>(products.values()));
        return "product_list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product",new Product(0,"",0,""));
        return "add_product_form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, Model model) {
        if(products.containsKey(product.id())){
            model.addAttribute("error","존재하는 ID 입니다.");
            model.addAttribute("product",product);
            return "add_product_form";
        }
        products.put(product.id(), product);
        return "redirect:/admin/products";
    }
}
