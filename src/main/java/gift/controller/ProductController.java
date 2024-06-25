package gift.controller;

import gift.Product;
import gift.repository.MemoryProductRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final MemoryProductRepository repository;

    public ProductController(MemoryProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public List<Product> getAllProducts() {
        List<Product> products = repository.findAll();
        return products;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = repository.findById(id);
        return product;
    }


}
