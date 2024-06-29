package gift.product.controller;

import gift.product.model.Product;
import gift.product.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    public static final String REDIRECT_ADMIN_PRODUCTS = "redirect:/admin/products";
    private final ProductRepository productRepository;

    @Autowired
    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @PostMapping
    public String deleteProduct(Product product) {
        productRepository.delete(product.getId());
        return REDIRECT_ADMIN_PRODUCTS;

    }

    @GetMapping("/insert")
    public String insertForm() {
        return "admin/insertForm";
    }

    @PostMapping("/insert")
    public String insertProduct(Product product) {
        productRepository.save(product);
        return REDIRECT_ADMIN_PRODUCTS;
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Long id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "admin/updateForm";
    }

    @PostMapping("/update/{id}")
    public String updateForm(Product product) {
        productRepository.update(product);
        return REDIRECT_ADMIN_PRODUCTS;
    }
}
