package gift.controller;

import gift.DTO.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// RestController : 데이터 반환
// Controller : View 반환
@Controller
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    // 상품 목록 출력
    // 기존 : Json 배열 형식의 반환 ( @RestController )
    // 현재 : UI를 입힌 View 를 반환하는 것으로 변경 ( @Controller )
    // 이전 화면 : ? ( GET 요청 이므로, 어디서든 접근 가능 ) -> 현재 -> 다음 화면 : 상품 추가 or 수정 폼
    @GetMapping("/api/products")
    public String findAll(Model model) {
        model.addAttribute("products", products.values());
        return "products.html";
    }

    // PRG ( Post Redirect Get ) 패턴 적용, 상품 추가 이후 상품 조회로 리다이렉트
    // + 추가 : return 시, /api/products를 GET 요청으로 받아오지 않으면 상품 정보가 출력되지 않을 것이다
    // 이전 화면 : 상품 추가 폼 -> 현재 -> 다음 화면 : 상품 목록 ( 추가한 물품 반영 )
    @PostMapping("/api/products")
    public String addProduct(Product product){
        products.put(product.getId(), product);
        return "redirect:/api/products";
    }

    // 상품 삭제, 삭제 작업 이후 상품 정보 화면으로 돌아가기
    // 이전 화면 : 상품 목록 -> 현재 -> 다음 화면 : 상품 목록 ( 삭제한 물품 반영 )
    @DeleteMapping("/api/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Long data){
        products.remove(data);
        return "redirect:/api/products";
    }

    // 상품 수정, 수정 작업 이후 상품 정보 화면으로 돌아가기
    // 이전 화면 : 상품 수정 폼 -> 현재 -> 다음 화면 : 상품 목록
    @PutMapping("/api/products/{productId}")
    public String modifyProduct(@PathVariable("productId") Long data, Product product){
        products.remove(data);
        products.put(data, product);
        return "redirect:/api/products";
    }
}