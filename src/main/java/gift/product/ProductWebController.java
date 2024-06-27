package gift.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ProductWebController {
    private final ProductService productService;

    @Autowired
    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }
}