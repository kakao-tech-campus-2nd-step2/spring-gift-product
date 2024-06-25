package gift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong listId = new AtomicLong(1);

    @PostMapping("/api/products")
    public void CreateProducts(@RequestParam("id") Long id, @RequestParam("name") String name,
        @RequestParam("price") int price, @RequestParam("imageUrl") String imgUrl) {
        var product = new Product(id, name, price, imgUrl);
        products.put(listId.getAndIncrement(), product);
    }

    @GetMapping("/api/products")
    public String ReadProducts() throws JsonProcessingException {
        if (products.isEmpty()) {
            return "Empty";
        }
        var objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(new ArrayList<Product>(products.values()));
    }

    @PutMapping("/api/products")
    public void UpdateProductsName(@RequestParam(name = "id") Long id, @RequestParam(name = "name") String name) {
        var product = products.get(id);
        var newProduct = new Product(product.id(), name, product.price(), product.imageUrl());
        products.remove(id);
        products.put(listId.getAndIncrement(), newProduct);
    }

}
