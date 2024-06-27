package gift.controller;

import gift.dto.CreateProduct;
import gift.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
