package gift;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class ViewController {
    private final ProductService productService;

    public ViewController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products")
    public String products(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        return "product_list.html";
    }
}
