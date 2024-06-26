package gift.controller;

import gift.domain.Product;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    // 상품 조회 기능(전체 조회)
    @GetMapping()
    public Collection<Product> getAllProduct() {
        Collection<Product> val = products.values();
        return val;
    }

    // 상품 조회 기능(id에 따른 조회)
    @GetMapping("/{id}")
    public Product getProductbyId(@RequestParam(value = "id") Long id) {
        return products.get(id);
    }

    // 상품 추가 기능
    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        var prod = new Product(newProduct.getId(), newProduct.getName(), newProduct.getPrice(),
            newProduct.getImageUrl());
        products.put(prod.getId(), prod);
        return new ResponseEntity<>(prod, HttpStatus.CREATED);
    }

    // 상품 수정 기능
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestParam Long id, @RequestBody Product updProduct) {
        Product prod = products.get(id);
        if (prod == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        prod.setId(updProduct.getId());
        prod.setName(updProduct.getName());
        prod.setPrice(updProduct.getPrice());
        prod.setImageUrl(updProduct.getImageUrl());
        products.put(id, prod);

        return new ResponseEntity<>(prod,HttpStatus.OK);
    }

    // 상품 삭제 기능
    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@RequestParam(value = "id") Long id) {
        products.remove(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }





}
