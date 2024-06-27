package gift.controller;

import gift.dto.CreateProduct;
import gift.dto.ProductDTO;
import gift.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/api/products")
    public void createProduct(
            @RequestBody CreateProduct.Request request
            )
    {
        productService.createProduct(request);
    }

    @GetMapping("/api/products")
    public Map<Long,ProductDTO> getAllProducts() {
        return  productService.getAllProducts();
    }
}
