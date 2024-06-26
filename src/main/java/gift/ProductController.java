package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/products")
    public ResponseEntity makeProduct(@RequestBody ProductRequestDto requestDto) {
        Product product = new Product(
                requestDto.getId(),
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        if (products.get(requestDto.getId()) == null) {
            products.put(requestDto.getId(), product);
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

}
