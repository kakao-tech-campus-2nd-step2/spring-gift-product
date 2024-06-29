package com.kakaotech2.j20.controller;

import com.kakaotech2.j20.DTO.ProductDTO;
import com.kakaotech2.j20.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        System.out.println(pm.readAll().stream().count());
        model.addAttribute("productDTO",new ProductDTO());
        return "admin/index";//렌더링하는 html 이름
    }

    @PostMapping("/admin") //admin으로 오는 post에 대해서 submit
    public String adminPageSubmit(@ModelAttribute("productDTO") ProductDTO productDTO) {
        pm.create(productDTO); //서비스에 접근해서 해당 부분을 추가해주도록 한다.
        return "redirect:/admin";
    }
}
