package gift.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FormController {
    // Product 정보 입력을 위한 HTML Form 열기
    @GetMapping("/api/products/addForm")
    public String addForm(){
        return "productAddForm.html";
    }

    @GetMapping("/api/products/modForm/{productId}")
    public String modForm(@PathVariable("productId") Long product_id, Model model){
        model.addAttribute("id", product_id);
        return "productModForm.html";
    }
}
