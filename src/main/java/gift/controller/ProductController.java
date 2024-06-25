package gift.controller;

import gift.entity.Product;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public Product getProducts() {
    }

    @PostMapping("/api/product")
    public void addProduct() {
    }

    @DeleteMapping("api/product/{id}")
    public void deleteProduct(@PathVariable int id) {
    }

    @PutMapping("api/product/{id}")
    public void editProduct(@PathVariable int id){

    }
}
