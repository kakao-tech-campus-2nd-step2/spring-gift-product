package gift;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Object addProduct(@RequestBody Product product) {
        String result = productService.addProduct(product);
        if (result != null) {
            return result; // 오류 메시지 반환
        }
        return product; // 성공 시 추가된 Product 반환
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id); // 성공 시 null 반환, 오류 시 오류 메시지 반환
    }
}