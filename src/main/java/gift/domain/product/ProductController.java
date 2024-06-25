package gift.domain.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> productRepository = new ConcurrentHashMap<>();
    private final AtomicLong key = new AtomicLong(1);

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product productDTO) {
        Product product = new Product(key.getAndIncrement(), productDTO.getName(),
                                        productDTO.getPrice(), productDTO.getImageUrl());
        productRepository.put(product.getId(), product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Product>> readAll() {
        List<Product> productList = new ArrayList<>(productRepository.values());
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }
}
