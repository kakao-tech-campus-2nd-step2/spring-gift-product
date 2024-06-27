package gift.Controller;

import gift.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        return "redirect:/api/getAllProducts";
    }

    @GetMapping("/postform")
    public String postform(){
        return "postform";
    }
}
