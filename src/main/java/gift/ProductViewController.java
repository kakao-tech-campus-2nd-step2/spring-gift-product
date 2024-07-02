package gift;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductViewController {

    @GetMapping("/products")
    public String showProductList() {
        return "products";
    }

    @GetMapping("/products/new")
    public String showAddForm() {
        return "new";
    }

    @GetMapping("/products/edit/{id}")
    public String editProduct() {
        return "edit";
    }
}