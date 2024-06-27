package gift.Controller;

import gift.Model.Product;
import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
