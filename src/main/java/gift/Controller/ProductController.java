package gift.Controller;

import gift.Product;
import gift.ProductRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductRepository productRepository = new ProductRepository;

    @PostMapping
    @ResponseBody
    private ResponseEntity<Product> addProduct(@RequestBody Product product){
        productRepository.addProduct(product);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

}
