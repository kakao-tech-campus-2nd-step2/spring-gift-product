package gift.controller;

import gift.Product;
import gift.ProductDTO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/api/product")
    public void registerProduct(@RequestBody ProductDTO productDTO) {
        System.out.println(productDTO);
        products.put(productDTO.id(), productDTO.toEntity());
    }
}
