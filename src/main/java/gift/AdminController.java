package gift;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


@Controller
public class AdminController {
    final String adminpath = "/admin/products";
    final ProductDAO productDAO;

    public AdminController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping(adminpath)
    public String getProduct(Model model) {
        model.addAttribute("products", productDAO.selectAll());
        return "admin";
    }

    @GetMapping(adminpath + "/add")
    public String addProduct() {
        return "add";
    }
    @PostMapping(adminpath + "/add")
    public String addProduct(@ModelAttribute("product") Product product) {
        productDAO.insert(product);
        return "redirect:" + adminpath;
    }
    @GetMapping(adminpath + "/del/{id}")
    public String delProduct(@PathVariable int id) {
        productDAO.delete(id);
        return "redirect:" + adminpath;
    }

    @RequestMapping(adminpath + "/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productDAO.select(id));
        return "edit";

    }
    @PostMapping(adminpath + "/edit/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute("product") Product product) {
        productDAO.update(id, product);
        return "redirect:" + adminpath;
    }
}
