package gift.controller;

import gift.service.ProductService;
import gift.domain.model.ProductDto;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private String populateModelWithProducts(Model model) {
        List<ProductDto> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping()
    public String getProduct(@RequestParam Long id, Model model) {
        try {
            productService.getProduct(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return populateModelWithProducts(model);
    }


    @GetMapping("/all")
    public String getAllProduct(Model model) {
        try {
            List<ProductDto> products = productService.getAllProduct();
            model.addAttribute("products", products);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "index";
    }

    @PostMapping("/add")
    public String addProduct(@RequestBody ProductDto productDto, Model model) {
        try {
            productService.addProduct(productDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return populateModelWithProducts(model);
    }

    @PostMapping("/update")
    public String updateProduct(@RequestBody ProductDto productDto, Model model) {
        try {
            productService.updateProduct(productDto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return populateModelWithProducts(model);
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Long id, Model model) {
        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return populateModelWithProducts(model);
    }
}
