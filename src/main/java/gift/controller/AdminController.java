package gift.controller;

import gift.model.Product;
import gift.model.ProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

    private final ProductDao productDao;

    public AdminController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Product> products = productDao.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("admin/product/new")
    public String newProduct() {
        return "new";
    }

    @PostMapping("admin/product/new")
    public String newProduct(@ModelAttribute ProductRequest request) {
        productDao.save(request);
        return "redirect:/";
    }

    @PostMapping("/admin/product/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        productDao.deleteById(id);
        return "redirect:/";
    }
}
