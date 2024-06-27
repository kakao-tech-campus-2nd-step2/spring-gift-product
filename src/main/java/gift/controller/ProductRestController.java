package gift.controller;

import gift.dto.Product;
import gift.db.ProductMemoryDB;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductRestController {
    ProductMemoryDB productDB = ProductMemoryDB.getInstance();
    //Create
    //상품 추가
    @PostMapping("api/products")
    public String addProduct(@RequestBody Product product) {
        if (productDB.hasProductId(product.getId())) {
            return "추가 실패, 해당 id를 가진 상품이 이미 존재합니다.";
        }
        productDB.addProduct(product);
        return "상품 추가 성공!";
    }

    //Read
    //등록된 상품들의 리스트 반환
    @GetMapping("api/products")
    public List<Product> getProducts() {
        return productDB.getProducts();
    }

    //Update
    //해당 id를 가진 상품 정보를 수정
    @PutMapping("api/products/{id}")
    public String updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        if (productDB.hasProductId(id)) {
            productDB.addProduct(product);
            return "상품 수정 성공!";
        }
        return "수정 실패, 해당 id를 가진 상품이 존재하지 않습니다";
    }

    //Delete
    //해당 id를 가진 상품을 삭제
    @DeleteMapping("api/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        if (productDB.hasProductId(id)) {
            productDB.removeProduct(id);
            return "상품 삭제 성공!";
        }
        return "삭제 실패, 해당 id를 가진 상품이 존재하지 않습니다";
    }

}