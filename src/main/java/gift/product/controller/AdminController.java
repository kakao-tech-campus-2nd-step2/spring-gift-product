package gift.product.controller;

import gift.product.model.Product;
import gift.product.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    private final ProductRepository productRepository;


    public AdminController() {
        this.productRepository = new ProductRepository();
    }

    @GetMapping
    public String products(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "admin/products";
    }

    @PostMapping
    public String deleteProduct(Product product, RedirectAttributes redirectAttributes) {
        productRepository.delete(product.getId());
        return "redirect:/admin/products";
    }

    @GetMapping("/insert")
    public String insertForm() {
        return "admin/insertForm";
    }

    @PostMapping("/insert")
    public String insertProduct(Product product, RedirectAttributes redirectAttributes) {
        productRepository.save(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable(name = "id") Long id, Model model) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "admin/updateForm";
    }

    @PostMapping("/update/{id}")
    public String updateForm(Product product, RedirectAttributes redirectAttributes) {
        productRepository.update(product);
        return "redirect:/admin/products";
    }

//    @PostConstruct
//    public void init() {
//        productRepository.save(new Product("아이스 카페 아메리카노 T", 4500,
//            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
//        productRepository.save(new Product("사과", 3000, "사과링크1"));
//    }
}
