package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductViewController {
    private final ProductController productController;

    public ProductViewController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productController.getAllProducts().getBody());
        return "product-list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product(null, "", 0, ""));
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productController.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productController.getProductById(id).getBody();
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id, Product product) {
        productController.updateProduct(id, product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productController.deleteProduct(id);
        return "redirect:/products";
    }
}
