package gift;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class AdminController {

    ProductController productController = new ProductController();

    @GetMapping("/admin")
    public String mainRendering(){
        return "main";
    }

    //GET
    @GetMapping("/admin/get")
    public String adminGetPage(Model model){
        Collection<Product> products = productController.getProducts();
        model.addAttribute("products", products);
        return "get";
    }

    //ADD
    @GetMapping("/admin/post")
    public String adminAddPage(){
        return "add";
    }

    @PostMapping("/admin/post/submit")
    public String submitPostProduct(@ModelAttribute Product product, Model model) {
        productController.addProduct(product);
        model.addAttribute("products", productController.getProducts());
        return "get";
    }

}
