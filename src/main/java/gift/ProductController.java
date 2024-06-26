package gift;

import java.util.Collection;
import java.util.HashMap;
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

    // 모든 상품 Read
    @GetMapping("/api/products")
    public Collection<Product> readAll(){
        return products.values();
    }

    // 특정 상품 Read
    @GetMapping("/api/products/{id}")
    public Product read(@PathVariable("id") Long id){
        return products.get(id);
    }
    // 새 상품 Create
    @PostMapping("/api/products")
    public Product create(@RequestBody ProductDto productDto){
        Product product = new Product(productDto.getName(), productDto.getPrice(), productDto.getImageUrl());
        products.put(product.getId(), product);
        return product;
    }

    @PutMapping("/api/products/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        Product product = products.get(id);
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());

        products.replace(product.getId(), product);
        return product;
    }

    @DeleteMapping("/api/products/{id}")
    public Product delete(@PathVariable("id") Long id){
        products.remove(id);
        return null;
    }
}
