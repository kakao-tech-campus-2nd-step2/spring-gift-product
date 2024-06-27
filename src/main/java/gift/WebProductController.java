package gift;

import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebProductController {
    private final ProductController productController;

    public WebProductController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping("/products")
    public String ViewHomepage(Model model) {
        Collection<Product> products = productController.getAllProducts().getBody();
        model.addAttribute("productsList", products);
        return "index";
    }

    @GetMapping("/showNewProducts")
    public String showNewProducts(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "newProduct";
    }

}
