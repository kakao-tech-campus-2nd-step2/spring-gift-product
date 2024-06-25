package gift.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import gift.controller.dto.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        var newProduct = new Product(product.id(), product.name(), product.price(),
            product.imageUrl());
        products.put(orderId.getAndIncrement(), newProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }

    @GetMapping("/api/products")
    public ResponseEntity<Collection<Product>> GetAllProducts() {
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(products.values());
    }

    @GetMapping("/api/products/{id}")
    public ResponseEntity<Product> GetProduct(@PathVariable(name = "id") Long id) {
        var productList = products.entrySet().stream().filter(x -> x.getValue().id().equals(id))
            .toList();
        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(productList.getFirst().getValue());
    }

    @PutMapping("/api/products")
    public ResponseEntity<Product> UpdateProductsName(@RequestParam(name = "id") Long id,
        @RequestParam(name = "name") String name) {
        var productList = products.entrySet().stream().filter(x -> x.getValue().id().equals(id))
            .map(Entry::getKey).toList();
        if (productList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var sampleProduct = products.get(productList.getFirst());
        productList.forEach(l -> updateElement(name, l, sampleProduct));
        return ResponseEntity.ok().body(products.get(productList.getFirst()));
    }

    private void updateElement(String name, Long l, Product sampleProduct) {
        products.remove(l);
        products.put(l, new Product(sampleProduct.id(), name, sampleProduct.price(),
            sampleProduct.imageUrl()));
    }

    @DeleteMapping("/api/products")
    public ResponseEntity<Product> DeleteProduct(@RequestParam(name = "id") Long productId) {
        var keyList = products.entrySet().stream().filter(x -> x.getValue().id().equals(productId))
            .map(Entry::getKey).toList();
        if (keyList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        keyList.forEach(products::remove);
        return ResponseEntity.noContent().build();
    }
}


