package gift.controller;

import gift.Product;
import gift.ProductDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/api/product")
    public void registerProduct(@RequestBody ProductDTO productDTO) {
        if (products.containsKey(productDTO.id())) {
            throw new IllegalArgumentException("이미 존재하는 상품입니다.");
        }
        products.put(productDTO.id(), productDTO.toEntity());
    }

    @GetMapping("/api/products")
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @GetMapping("/api/product/{id}")
    public Product getProduct(@PathVariable("id") Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        return products.get(id);
    }

    @PutMapping("/api/product/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody ProductDTO productDTO) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        Product product = products.get(id);
        product.setId(id);
        product.setName(productDTO.name());
        product.setPrice(productDTO.price());
        product.setImageUrl(productDTO.imageUrl());

        products.put(id, product);
    }

    @DeleteMapping("/api/product/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다.");
        }
        products.remove(id);
    }
}
