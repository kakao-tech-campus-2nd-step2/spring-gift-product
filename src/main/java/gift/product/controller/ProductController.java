package gift.product.controller;

import gift.product.model.ProductVo;
import gift.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductVo productVo, Model model) {
        productService.addProduct(productVo);
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", null);
        return "product";
    }

    @PostMapping("/modify")
    public String modifyProduct(@ModelAttribute ProductVo productVo, Model model) {
        productService.modifyProduct(productVo);
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", null);
        return "product";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Model model) {
        productService.deleteProduct(id);
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", null);
        return "product";
    }

    @GetMapping("/listup")
    public String listupProduct(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", null);
        return "product";  // Ensure this matches the name of your Thymeleaf template
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("searchResults", productService.searchProducts(keyword));
        model.addAttribute("keyword", keyword);
        return "product";  // Ensure this matches the name of your Thymeleaf template
    }
}
