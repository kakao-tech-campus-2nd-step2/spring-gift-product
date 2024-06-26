package gift.controller;

import gift.dto.Product;
import java.util.Collection;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong orderId = new AtomicLong(1);

    @PostMapping("/api/products")
    public ResponseEntity<?> AddProduct(@RequestBody Product product) {
        var newProduct = new Product(product.id(), product.name(), product.price(),
            product.imageUrl());
        if (newProduct.id() == null || newProduct.name().isEmpty() || newProduct.price() < 0
            || newProduct.imageUrl().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Request");
        }
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
    public ResponseEntity<?> GetProduct(@PathVariable(name = "id") Long id) {
        var productList = products.entrySet().stream().filter(x -> x.getValue().id().equals(id))
            .toList();
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("There is No Product with id:" + id);
        }
        return ResponseEntity.ok().body(productList.getFirst().getValue());
    }

    @PutMapping("/api/products/{id}")
    public ResponseEntity<?> UpdateProductsName(@PathVariable(name = "id") Long id,
        @RequestBody Product product) {
        if (product.id() == null || product.name().isEmpty() || product.price() < 0
            || product.imageUrl().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid Request");
        }
        var productList = products.entrySet().stream().filter(x -> x.getValue().id().equals(id))
            .map(Entry::getKey).toList();
        if (productList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product is not Exist");
        }
        products.remove(id);
        products.put(id, product);
        return ResponseEntity.ok().body(products.get(productList.getFirst()));
    }


    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable(name = "id") Long productId) {
        var keyList = products.entrySet().stream().filter(x -> x.getValue().id().equals(productId))
            .map(Entry::getKey).toList();
        var deletedProducts = keyList.stream()
            .map(products::get)
            .toList();
        if (keyList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product is not exist");
        }
        keyList.forEach(products::remove);
        return ResponseEntity.ok(deletedProducts);
    }
}


