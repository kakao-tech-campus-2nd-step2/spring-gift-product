package gift.controller.admin;

import gift.domain.Product;
import gift.service.ProductService;
import gift.util.ImageStorageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/products")
public class AdminProductController {
    private final ProductService productService;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam Long price,
                             @RequestParam String description,
                             @RequestPart String imageUrl) {

            // 제품 정보 저장
            Product product = new Product(null, name, price, description, imageUrl);
            productService.addProduct(product);

            return "redirect:/admin/products";

    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @RequestParam String name,
                                @RequestParam Long price,
                                @RequestParam String description,
                                @RequestPart String imageUrl) {
            Product product = productService.getProductById(id);


            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImageUrl(imageUrl);

            productService.updateProduct(product);

            return "redirect:/admin/products";

    }



}
