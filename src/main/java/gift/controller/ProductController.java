package gift.controller;

import gift.controller.dto.ProductCreateRequestDto;
import gift.controller.dto.ProductResponseDto;
import gift.controller.dto.ProductUpdateRequestDto;
import gift.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping
    public void addProduct(@RequestBody ProductCreateRequestDto productCreateRequest) {
        productService.addProduct(productCreateRequest);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id,
        @RequestBody ProductUpdateRequestDto productUpdateRequest) {
        productService.updateProduct(id, productUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
