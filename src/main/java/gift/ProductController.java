package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class ProductController {
    ProductMemoryDB productDB = ProductMemoryDB.getInstance();

    @GetMapping("/")
    public String getProducts(Model model) {

        model.addAttribute("products", productDB.getProducts());
        System.out.println("hello");
        return "version-SSR/index.html";
    }


}
