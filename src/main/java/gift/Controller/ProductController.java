package gift.Controller;

import gift.domain.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    @GetMapping
    public List<Product> getProducts(){

    }
    @PostMapping
    public ResponseEntity<Product> addProducts(@RequestBody Product product){

    }
    @PutMapping
    public ResponseEntity<Product> modifyProducts(@RequestBody Product product){

    }
    @DeleteMapping
    public ResponseEntity<Product> deleteProducts(@RequestBody Product product){

    }
}
