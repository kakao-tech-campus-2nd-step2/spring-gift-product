package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    //조회
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws Exception {
        if(!products.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found");
        }
        return products.get(id);
    }
}