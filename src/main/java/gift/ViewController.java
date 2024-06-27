package gift;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class ViewController {
    private final ProductController productController;

    public ViewController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping("")
    public String getAllProducts(Model model) {
        Map<Long, Product> products = productController.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable Long id, Model model) {
        Product product = productController.findById(id);
        model.addAttribute("product", product);
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product(0, "", 0, ""));
        return "add_product";
    }

    @PostMapping("")
    public String createProduct(@ModelAttribute Product product) {
        productController.createProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productController.findById(id);
        model.addAttribute("product", product);
        return "add_product";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productController.updateProduct(id, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productController.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
