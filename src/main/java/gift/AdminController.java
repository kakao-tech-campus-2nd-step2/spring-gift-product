package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final ProductDao productDao;

    public AdminController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/api/admin")
    public String admin(Model model) {
        model.addAttribute("products", productDao.getAllProducts());
        return "administrator";
    }
}
