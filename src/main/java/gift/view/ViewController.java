package gift.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController
{
    @GetMapping("/api/add_product")
    public String showAddProductForm() {
        return "api/add_product";
    }
    @GetMapping("/api/modify_product")
    public String showModifyProductForm() {
        return "api/modify_product";
    }
}
