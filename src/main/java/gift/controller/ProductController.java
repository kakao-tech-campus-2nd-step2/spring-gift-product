package gift.controller;

import gift.dto.Product;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.*;

@Controller
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProducts(Model model) {
        List<Product> products = productService.getAllProducts();

        model.addAttribute("products", products);
        return "productList";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        productService.addProduct(product);

        return "redirect:/api/products";  // 새로운 상품 추가 후 상품 조회 화면으로 리다이렉트
    }

    @GetMapping("/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @GetMapping("/{id}/edit")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id);

        if(product == null) {
            return "redirect:/api/products";
        }

        model.addAttribute("product", product);

        return "editProduct";
    }

    @PostMapping("/{id}")
    public String EditProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        // 상품 정보 수정
        productService.updateProduct(id, product);

        return "redirect:/api/products";
    }

    @GetMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        // 요청받은 id를 가진 상품을 삭제
        productService.deleteProduct(id);

        return "redirect:/api/products";
    }

}
