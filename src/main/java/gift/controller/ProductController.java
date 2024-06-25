package gift.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gift.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong orderId = new AtomicLong(1);

    @PostMapping("/api/products")
    public ResponseEntity<Product> AddProduct(@RequestBody Product product) {
        var prdct = new Product(product.id(), product.name(), product.price(), product.imageUrl());
        products.put(orderId.getAndIncrement(), prdct);
        return ResponseEntity.status(HttpStatus.OK).body(prdct);
    }

    @GetMapping("/api/products")
    public ResponseEntity<Collection<Product>> GetAllProducts() throws JsonProcessingException {
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(products.values());
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
        productList.forEach(l -> updateElement(name, l, sampleProduct));
    }

    private void updateElement(String name, Long l, Product sampleProduct) {
        products.remove(l);
        products.put(l, new Product(sampleProduct.id(), name, sampleProduct.price(),
            sampleProduct.imageUrl()));
    }

    @DeleteMapping("/api/products")
    public void DeleteProduct(@RequestParam(name = "id") Long productId) {
        var keyList = products.entrySet().stream().filter(x -> x.getValue().id().equals(productId))
            .map(Entry::getKey).toList();
        if (keyList.isEmpty()) {
            return;
        }
        keyList.forEach(products::remove);
    }
}


