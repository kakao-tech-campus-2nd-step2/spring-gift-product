package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gift.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public void UpdateProductsName(@RequestParam(name = "id") Long id,
        @RequestParam(name = "name") String name) {
        var productList = products.entrySet().stream().filter(x -> x.getValue().id().equals(id))
            .map(Entry::getKey).toList();
        if (productList.isEmpty()) {
            return;
        }
        var sampleProduct = products.get(productList.getFirst());
        productList.stream().forEach(l -> {
            products.remove(l);
            products.put(l, new Product(sampleProduct.id(), name, sampleProduct.price(),
                sampleProduct.imageUrl()));
        });
    }

    @DeleteMapping("/api/products")
    public void DeletePRoduct(@RequestParam(name = "id") Long productId) {
        var keyList = products.entrySet().stream().filter(x -> x.getValue().id().equals(productId))
            .map(Entry::getKey).toList();
        if (keyList.isEmpty()) {
            return;
        }
        keyList.forEach(products::remove); // 왜 stream이 필요가 없어지는지? forEach가 stream을 포함하고 있는 관계?
    }
}
