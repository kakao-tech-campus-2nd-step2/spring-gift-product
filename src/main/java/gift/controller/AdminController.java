package gift.controller;

import gift.model.ProductRecord;
import gift.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    private ProductDAO productDAO;

    @GetMapping("/")
    public String admin(Model model) {
        ProductRecord[] products = productDAO.getAllRecords();

        model.addAttribute("products", products);
        return "admin";
    }
}
