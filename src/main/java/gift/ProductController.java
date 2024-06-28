package gift;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1L;

    @PostConstruct
    public void init() {
        Product product1 = new Product();
        product1.setId(8146027);
        product1.setName("아이스 카페 아메리카노 T");
        product1.setPrice(4500);
        product1.setImageUrl("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        products.put(product1.getId(), product1);
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("product", new Product());
        return "product-list";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            productRepository.save(product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error adding product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        try {
            product.setId(id);
            productRepository.update(product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error updating product: " + e.getMessage());
        }
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting product: " + e.getMessage());
        }
        return "redirect:/products";
    }

}
    @GetMapping("/view/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Product product = productRepository.findById(id);
            model.addAttribute("product", product);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Product not found: " + e.getMessage());
            return "redirect:/products";
        }
        return "product-detail";
    }

