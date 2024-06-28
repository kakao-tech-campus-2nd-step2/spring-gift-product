package gift.controller;

import gift.ProductService;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebController {
    private final ProductService productService = new ProductService();

    @GetMapping("/lists")
    public String itemList(Model model){
        List<ProductResponseDto> products = productService.findAll();
        model.addAttribute("products",products);
        return "items";
    }

    @GetMapping("/add")
    public String add(){
        return "addForm";
    }
}
