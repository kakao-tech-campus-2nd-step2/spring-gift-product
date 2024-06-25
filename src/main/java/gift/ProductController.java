package gift;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductService productService = new ProductService();

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        try {
            return productService.getProductById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
