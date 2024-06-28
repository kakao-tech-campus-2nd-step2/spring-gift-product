package gift.controller;

import gift.dto.ProductRequestDto;
import gift.model.Product;
import gift.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public void addProduct(@RequestBody ProductRequestDto productRequestDto) {
        productService.addProduct(productRequestDto);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody ProductRequestDto productRequestDto) {
        productService.updateProduct(id, productRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
}
