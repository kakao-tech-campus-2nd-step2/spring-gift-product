package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> productsList = new ArrayList<>();

        products.forEach((key, value) -> {
            productsList.add(value);
        });

        return new ResponseEntity(productsList, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        Product product = products.get(id);

        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/products")
    public ResponseEntity putProduct(@RequestBody ProductRequestDto requestDto) {
        Product originProduct = products.get(requestDto.getId());

        if (originProduct == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        products.remove(requestDto.getId());

        Product updateProduct = new Product(
                requestDto.getId(),
                requestDto.getName(),
                requestDto.getPrice(),
                requestDto.getImageUrl()
        );

        products.put(requestDto.getId(), updateProduct);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
