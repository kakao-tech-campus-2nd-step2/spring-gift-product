package gift;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    //Create
    //상품 추가
    @PostMapping("api/products")
    public String addProduct(@RequestBody Product product) {
        products.put(product.getId(), product);
        return "상품 추가 성공!";
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
    public String updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        if (products.containsKey(id)) {
            products.put(id, product);
            return "상품 수정 성공!";
        }
        return "수정 실패, 해당 id를 가진 상품이 존재하지 않습니다";
    }
    //Delete
    //해당 id를 가진 상품을 삭제
    @DeleteMapping("api/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
            return "상품 삭제 성공!";
        }
        return "삭제 실패, 해당 id를 가진 상품이 존재하지 않습니다";
    }

}
