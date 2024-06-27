package gift.controller;

import gift.DTO.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    // Product 정보 조회를 위한 GET 요청에 대한 응답
    // 기존에 Json 배열 형식의 반환을 UI를 입힌 View 를 반환하는 것으로 변경
    @GetMapping("/api/products")
    public String findAll(Model model) {
        model.addAttribute("products", products.values());
        return "products.html";
    }

    // PRG ( Post Redirect Get ) 패턴 적용, 상품 추가 이후 상품 조회로 리다이렉트
    @PostMapping("/api/products")
    public String addProduct(Product product){
        products.put(product.getId(), product);
        return "redirect:/api/products";
    }

    // 위와 같은 사유로 상품 삭제 이후 상품 조회로 리다이레트
    @DeleteMapping("/api/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long data){
        products.remove(data);
        return "redirect:/api/products";
    }

    // 상품 수정
    @PutMapping("/api/products/{productId}")
    public String modifyProduct(@PathVariable("productId") Long data, Product product){
        products.remove(data);
        products.put(data, product);
        return "redirect:/api/products";
    }
}