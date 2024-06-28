package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product")
public class AdminController {

    @Autowired
    private ProductOperation productOperation;

    @GetMapping("/list")
    public String productList(Model model) {
        List<Product> products = productOperation.getAllProduct();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/add")
    public ModelAndView showAddPage() {
        ModelAndView modelAndView = new ModelAndView("product-add");
        modelAndView.addObject("product", new Product(0L, "", 0L, ""));
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product-edit");
        Product product = productOperation.getProductById(id);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product p) {
        Product product = productOperation.createProduct(p);
        return "redirect:/admin/product/list";
    }

    @PutMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productOperation.updateProduct(product.getId(), product);
        return "redirect:/admin/product/list";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        boolean check = productOperation.deleteProduct(id);
        if (check) {
            return ResponseEntity.ok("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    }
}