package gift.api;

import gift.dto.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductController productController;

    @Autowired
    public AdminController(ProductController productController) {
        this.productController = productController;
    }

    // 상품 조회
    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("productList", productController.getAllProducts());
        return "admin-product-list";
    }

    // 상품 추가 폼 표시
    @GetMapping("/add")
    public String showAddProductForm() {
        return "product-add-form";
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public Long deleteProduct(@PathVariable("id") Long id) {
        return productController.deleteProduct(id);
    }

    // 상품 수정
    @PatchMapping("/{id}")
    public Long updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return productController.updateProduct(id, request);
    }
}
