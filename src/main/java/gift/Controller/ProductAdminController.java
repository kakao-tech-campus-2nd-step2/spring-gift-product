package gift.Controller;

import gift.Model.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class ProductAdminController {
    private final ProductController productController;

    public ProductAdminController(ProductController productController) {
        this.productController = productController;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<ProductModel> products = productController.getAllProducts().getBody();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new ProductModel());
        return "product-form";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute ProductModel product) {
        ResponseEntity<ProductModel> response = productController.createProduct(product);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/admin/products";
        }
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        ProductModel product = productController.getProductById(id).getBody();
        if (product == null) {
            return "redirect:/admin/products";
        }
        model.addAttribute("product", product);
        return "product-form";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable long id, @ModelAttribute ProductModel product) {
        ResponseEntity<ProductModel> response = productController.updateProduct(id, product);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/admin/products";
        }
        return "product-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productController.deleteProduct(id);
        return "redirect:/admin/products";
    }
}

