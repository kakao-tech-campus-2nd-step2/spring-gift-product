package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class ProductWebController {
    private final ProductService productService;

    @Autowired
    public ProductWebController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProductsPage(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products")
    public String postProduct(@RequestParam String name, @RequestParam int price, @RequestParam String imageUrl) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        productService.createProduct(product);
        return "redirect:/products";
    }
    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam List<Long> productIds) {
        for (Long id : productIds) {
            productService.deleteProduct(id);
        }
        return "redirect:/products";
    }
    @GetMapping("/products/edit/{id}")
    public String getEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "productEdit";
    }
}