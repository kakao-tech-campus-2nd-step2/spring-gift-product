package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @GetMapping("/api/products")
    public Product product(@RequestParam("id") Long id,
        @RequestParam(value="name",required = false,defaultValue = "이춘식") String name,
        @RequestParam(value="price",required = false,defaultValue = "2000") int price,
        @RequestParam(value="imageUrl",required = false,defaultValue = "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg") String imageUrl){
        var product=new Product(id,name,price,imageUrl);
        return product;
    }
}