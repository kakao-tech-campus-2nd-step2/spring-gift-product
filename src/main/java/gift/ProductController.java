package gift;

import gift.exception.NotFoundProductException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    Long count = 1L;

    @PutMapping("/add")
    public Product addProduct(@RequestParam String name, @RequestParam int price, @RequestParam String imageUrl) {
        var product = new Product(count, name, price, imageUrl);
        products.put(count++,product);
        return product;
    }

    @PatchMapping("/update/{id}")
    public Product updateProduct(@PathVariable Long id,@RequestParam String name, @RequestParam int price, @RequestParam String imageUrl) {
        if(!products.containsKey(id)){
            throw new NotFoundProductException(id+"를 가진 상품이 존재하지 않습니다.");
        }
        var product = new Product(id, name, price, imageUrl);

        products.put(id,product);
        return product;
    }
}