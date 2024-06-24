package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    //Create
    //상품 추가
    @PostMapping("api/products")
    public void addProduct(@RequestBody Product product) {
        products.put(product.getId(), product);
    }
    //Read
    //등록된 상품들의 리스트 반환
    @GetMapping("api/products")
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();

        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(entry.getValue());
        }

        return productList;
    }
    //Update
    //해당 id를 가진 상품 정보를 수정
    @PutMapping("api/products/{id}")
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        products.put(id, product);
    }
    //Delete
    //해당 id를 가진 상품을 삭제
    @DeleteMapping("api/products/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        products.remove(id);
    }

}
