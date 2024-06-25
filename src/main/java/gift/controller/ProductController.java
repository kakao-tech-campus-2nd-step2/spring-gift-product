package gift.controller;


import gift.domain.Product;
import gift.domain.Products;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    Products products = new Products();

    //전체 product 목록 조회
    @GetMapping
    public Products getProduct(){
        return products;
    }

    //product 추가
    @PostMapping
    public String addProduct(@RequestBody Product product){
        if(products.add(product)){
            return "OK";
        }
        return "올바르지 않은 요청";
    }


}
