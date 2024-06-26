package gift.product.controller;

import gift.product.dto.ProductDTO;
import gift.product.model.Product;
import gift.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    @PostMapping("/insert")
    public ResponseEntity<Product> insertProduct(@RequestBody ProductDTO productDTO) {
        Product responseProduct = productService.insertProduct(productDTO);

        return ResponseEntity.ok(responseProduct);
    }
}
