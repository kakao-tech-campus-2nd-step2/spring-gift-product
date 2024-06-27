package gift.api;

import gift.application.ProductService;
import gift.domain.Product;
import gift.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;

    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }

    // 상품 조회
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "admin-product-list";
    }

    // 상품 추가 폼 표시
    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "admin-product-form";
    }

    // 상품 추가
    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest request) {
        productService.createProduct(request);
        return "admin-product-list";
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public Long deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProductById(id);
    }

    // 상품 수정
    @PatchMapping("/{id}")
    public Long updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return productService.updateProduct(id, request);
    }
}
