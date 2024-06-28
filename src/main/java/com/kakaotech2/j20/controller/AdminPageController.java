package com.kakaotech2.j20.controller;

import com.kakaotech2.j20.DTO.ProductDTO;
import com.kakaotech2.j20.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminPageController {

    private final ProductService pm;

    public AdminPageController(ProductService pm) {
        this.pm = pm;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("products",pm.readAll());
        return "admin/index";//렌더링하는 html 이름
    }
}
