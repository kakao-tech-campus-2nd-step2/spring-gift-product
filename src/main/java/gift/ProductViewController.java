package gift;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductViewController {
    private List<Product> products; // 상품 데이터

    @GetMapping("/products")
    public String showProductList(Model model) {
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