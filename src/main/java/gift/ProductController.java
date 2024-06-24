package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    //Product 클래스를 저장하는 해시맵
    private final Map<Long, Product> producsts = new HashMap<>();

    // 접속 주소 : localhost:8080/api/products
    @GetMapping("/api/products")
    public Product product(@RequestParam("id") Long id) {
        // product 객체 생성
        var product = new Product(id, "라이언 볼펜", 1000, "https://kakao.com/img.jpg");
        // 해시맵에 추가
        putProduct(id, product);

        return producsts.get(id);
    }
    public void putProduct(long id, Product product) {
        producsts.put(id, product);
    }
}