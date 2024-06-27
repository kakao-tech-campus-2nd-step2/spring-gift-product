package gift.controller;

import gift.model.Product;
import gift.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.getAllProducts());
        return "list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product()); // 빈 Product 객체를 생성하여 모델에 추가
        return "create";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product) {
        productRepository.addProduct(product);
        return "redirect:/admin/products"; // DB에 상품 추가 후 목록 페이지로 리다이렉트
    }

    @GetMapping("/update/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Product product = productRepository.getProduct(id);
        model.addAttribute("product", product);
        return "update";
    }

    @PostMapping("/update/{id}")
    public String editProduct(@PathVariable Long id, @ModelAttribute Product product) {
        productRepository.updateProduct(id, product);
        return "redirect:/admin/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productRepository.removeProduct(id);
        return "redirect:/admin/products";
    }
}
