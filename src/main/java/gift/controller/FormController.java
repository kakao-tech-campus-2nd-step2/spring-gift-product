package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FormController {
    // Product를 추가하기 위해 정보 입력 HTML Form 열기
    // 이전 화면 : 상품 목록 -> 현재 -> 다음 화면 : 상품 추가 폼
    @GetMapping("/api/products/addForm")
    public String addForm(){
        return "productAddForm.html";
    }

    // Product를 수정하기 위해 정보 입력 HTML Form 열기
    // 이전 화면 : 상품 목록 -> 현재 -> 다음 화면 : 상품 수정 폼
    @GetMapping("/api/products/modForm/{productId}")
    public String modForm(@PathVariable("productId") Long product_id, Model model){
        model.addAttribute("id", product_id);
        return "productModForm.html";
    }
}
