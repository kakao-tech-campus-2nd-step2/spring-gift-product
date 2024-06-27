package gift;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public Product getProduct(@RequestParam Long id) {
        try {
            return productService.getProduct(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @GetMapping("/all")
    public List<Product> getAllProduct() {
        try {
            return productService.getAllProduct();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
