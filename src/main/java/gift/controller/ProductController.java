package gift.controller;

import gift.controller.request.ProductCreateRequest;
import gift.domain.Product;
import gift.service.ProductService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> productList() {
        return ResponseEntity.ok()
            .body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<Product> productAdd(@RequestBody ProductCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(productService.addProduct(request));
    }

}
