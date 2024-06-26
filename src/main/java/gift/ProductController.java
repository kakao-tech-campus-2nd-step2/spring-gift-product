package gift;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @GetMapping("/api/products")
    public String readProducts(){

    }
    @PostMapping("/api/products")
    public String createProducts(){

    }

    @PutMapping("/api/products/{name}")
    public String updateProduct(){

    }

    @DeleteMapping("/api/products/{name}")
    public String deleteProduct(){

    }
}
