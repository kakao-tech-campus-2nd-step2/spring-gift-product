package gift.controller;

import gift.ProductService;
import gift.domain.product.Product;
import gift.dto.ProductRequestDto;
import gift.dto.ProductResponseDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/edit/{id}")
    public String editProduct(
            @PathVariable("id") Long id, Model model){
        ProductResponseDto product = productService.findProduct(id);
        model.addAttribute("product",product);
        return "editForm";
    }
}
