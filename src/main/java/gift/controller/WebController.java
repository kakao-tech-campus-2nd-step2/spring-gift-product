package gift.controller;

import gift.ProductService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    private final ProductService productService = new ProductService();

    @GetMapping("/lists")
    public String itemList(Model model){
        model.addAttribute("products",productService.findAll());
        return "items";
    }
}
