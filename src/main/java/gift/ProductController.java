import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import gift.*;

@RestController
@RequestMapping("/hello")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    // get all product
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(new ArrayList<>(products.values()), HttpStatus.OK);
    }


}