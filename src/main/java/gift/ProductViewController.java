package gift;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/admin")
public class ProductViewController {

    private final RestTemplate restTemplate;

    public ProductViewController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public String showProductManagementPage(Model model) {
        try {
            Map<Long, Product> products = restTemplate.getForObject("http://localhost:8080/api/products", Map.class);
            if (products == null) {
                model.addAttribute("products", List.of());
                return "productManagement"; // 템플릿 파일 이름
            }
            model.addAttribute("products", products.values());
        } catch (Exception e) {
            model.addAttribute("error", "Failed to load products: " + e.getMessage());
        }
        return "productManagement"; // admin 템플릿 파일 이름
    }
}
