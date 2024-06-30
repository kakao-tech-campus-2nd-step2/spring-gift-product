package gift.controller;

import gift.db.ProductDB;
import gift.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductRestController {
    @Autowired
    @Qualifier("MEMORY DATABASE")
    private ProductDB productDB;

    //Create
    //상품 추가
    @PostMapping("api/products")
    public String addProduct(@RequestBody Product product) {
        try {
            productDB.addProduct(product);
            return "상품 추가 성공!";
        } catch (Exception e) {
            return "추가 실패, 해당 id를 가진 상품이 이미 존재합니다.";
        }
    }

    //Read
    //등록된 상품들의 리스트 반환
    @GetMapping("api/products")
    public List<Product> getProducts() {
        return productDB.getProducts();
    }

    //Update
    //해당 id를 가진 상품 정보를 수정
    @PutMapping("api/products/")
    public String updateProduct(@RequestBody Product product) {
        try {
            productDB.editProduct(product);
            return "상품 수정 성공!";
        } catch (Exception e) {
            return "수정 실패, 해당 id를 가진 상품이 존재하지 않습니다";
        }
    }

    //Delete
    //해당 id를 가진 상품을 삭제
    @DeleteMapping("api/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        try {
            productDB.removeProduct(id);
            return "상품 삭제 성공!";
        } catch (Exception e) {
            return "삭제 실패, 해당 id를 가진 상품이 존재하지 않습니다";
        }
    }

}
