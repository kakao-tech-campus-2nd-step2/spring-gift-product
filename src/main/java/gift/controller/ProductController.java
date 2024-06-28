package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products"; // Thymeleaf 템플릿 파일 이름 (products.html)
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product.Builder().build());
        return "addProduct"; // Thymeleaf 템플릿 파일 이름 (addProduct.html)
    }

    @PostMapping
    public String addProduct(@RequestParam String name, @RequestParam int price, @RequestParam String imageUrl) {
        try {
            Product product = new Product.Builder()
                    .name(name)
                    .price(price)
                    .imageUrl(imageUrl)
                    .build();
            productService.addProduct(product);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 표시할 페이지 (error.html)
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct"; // Thymeleaf 템플릿 파일 이름 (editProduct.html)
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @RequestParam String name, @RequestParam int price, @RequestParam String imageUrl) {
        try {
            Product updatedProduct = new Product.Builder()
                    .id(id)
                    .name(name)
                    .price(price)
                    .imageUrl(imageUrl)
                    .build();
            productService.updateProduct(id, updatedProduct);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 표시할 페이지 (error.html)
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return "redirect:/products";
        } catch (Exception e) {
            e.printStackTrace();
            return "error"; // 에러 발생 시 표시할 페이지 (error.html)
        }
    }
}
