package gift;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class AdminController {
    final ProductDAO productDAO;

    public AdminController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @RequestMapping(value =  "/admin/products")
    public String getProduct(Model model) {
        model.addAttribute("products", productDAO.selectAll());
        return "admin";
    }

    @GetMapping("/admin/products/add")
    public String addProduct() {
        return "add";
    }
    @PostMapping("/admin/products/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productDAO.insert(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/admin/products/del/{id}")
    public String delProduct(@PathVariable int id) {
        productDAO.delete(id);
        return "redirect:/admin/products";
    }

    @RequestMapping("admin/products/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productDAO.select(id));
        return "edit";

    }
    @PostMapping("/admin/products/edit/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") Product product) {
        productDAO.update(id, product);
        return "redirect:/admin/products";
    }
}
