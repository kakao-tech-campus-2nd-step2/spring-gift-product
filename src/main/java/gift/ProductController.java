package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    //조회
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id) throws Exception {
        if(!products.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product Not Found");
        }
        return products.get(id);
    }

    //추가
    @PostMapping()
    public String postProduct(@RequestParam("id") Long id,
        @RequestParam("name") String name,
        @RequestParam("price") int price,
        @RequestParam("imageUrl") String imageUrl){
        if(products.containsKey(id)){
            return "존재하는 id 입니다.";
        }
        var product=new Product(id,name,price,imageUrl);
        products.put(id,product);
        return "추가되었습니다.";
    }
    //수정
    @PutMapping("/{id}")
    public Product putProduct(@PathVariable("id") Long id,@RequestBody Map<String, Object> putData) {
        if(!products.containsKey(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        }
        String name=products.get(id).getName();
        int price=products.get(id).getPrice();
        String imageUrl=products.get(id).getImageUrl();
        if(putData.containsKey("name")) {
            name = putData.get("name").toString();
        }
        if (putData.containsKey("price")) {
            try {
                price = Integer.parseInt(putData.get("price").toString());
            } catch (NumberFormatException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid price format");
            }
        }
        if(putData.containsKey("imageUrl")){
            imageUrl=putData.get("imageUrl").toString();
        }
        var product=new Product(id,name,price,imageUrl);
        products.put(id,product);
        return product;
    }
}