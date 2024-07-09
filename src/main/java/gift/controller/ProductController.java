package gift.controller;

import gift.dto.request.ProductRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("products")

public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public String getAll(Model model){
        List<ProductResponseDto> productDtos = productService.findAllProducts();
        model.addAttribute("productDtos", productDtos);
        return "manager";
    }

    @GetMapping("/new")
    public String addForm(){
        return "addForm";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute ProductRequestDto productDto){
        productService.addProduct(productDto);
        return "redirect:/products";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id,
                             Model model){
        ProductResponseDto productDto = productService.findProductById(id);

        model.addAttribute("productDto", productDto);

        return "editForm";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                             @RequestParam("price") int price){
        productService.updateProduct(id, price);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        productService.deleteProduct(id);
        return "redirect:/products";

    }

}
