package gift.controller;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductViewController {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long sequence = 0L;

    @GetMapping("/step2/products")
    public String getAllProducts(Model model) {
        List<Product> productsList = products.values().stream().toList();
        model.addAttribute("products", productsList);
        return "products";
    }

}
