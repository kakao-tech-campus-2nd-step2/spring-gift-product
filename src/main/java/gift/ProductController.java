package gift;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    //Product 클래스를 저장하는 해시맵
    private final Map<Long, Product> products = new HashMap<>();

    // 접속 주소 : localhost:8080/api/products
    @GetMapping("/api/products")
    public java.util.Collection <Product> getAllProduct() {
        // 해시맵에 포함된 모든 객체를 반환
        return products.values();
    }

    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable long id) {
        // 주어진 id에 해당하는 객체를 반환
        return products.get(id);
    }

    @PostMapping("/api/products")
    public String addProduct(@RequestBody Product product) {
        //json 입력을 받아 product 객체 만들고 해시맵에 저장
        products.put(product.id(), product);
        return "Product added successfully";

    }

    @DeleteMapping("/api/products/{id}")
    public String removeProduct(@PathVariable long id) {
        // 입력된 id에 해당하는 해시맵 내 객체 삭제
        try {
            products.remove(id);
            return "Product removed";
        }
        catch (NoSuchElementException e) {
            return "Product not found";
        }
    }

    @PutMapping("/api/products/{id}")
    public String updateProduct(@PathVariable long id, @RequestBody Product product) {
        // 입력된 id에 해당하는 해시맵 내 객체 수정
        products.put(id, product);
        return "Product updated successfully";
    }
}