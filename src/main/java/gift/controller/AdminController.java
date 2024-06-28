package gift.controller;

import gift.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/products/admin")
public class AdminController {

    private final ProductRepository repository;

    public AdminController(ProductRepository repository) {
        this.repository = repository;
    }

    // 모든 제품을 표시하는 관리자 페이지로 매핑
    @GetMapping("")
    public String adminPage(Model model) {
        model.addAttribute("products", repository.findAll());
        return "admin";
    }

    // 새 제품을 추가하는 페이지로 매핑
    @GetMapping("/add")
    public String getAdminAddPage() {
        return "adminAdd";
    }

    // 삭제된 제품을 보여주는 페이지로 매핑
    @GetMapping("/deleted")
    public String getAdminDeletedPage() {
        return "adminDeleted";
    }

    // 특정 ID의 제품 세부 정보를 보여주는 페이지로 매핑
    @GetMapping("/{id}")
    public String getAdminProductDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("product", repository.findById(id).orElse(null)); // Null을 처리하기 위해 orElse(null) 사용
        return "adminProductDetail";
    }
}
