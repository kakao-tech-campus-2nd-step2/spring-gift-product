package gift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class ProductController {
    private Map<Long, Product> products = new HashMap<>();
    private static Long count = 1L;

    @GetMapping("/api/products")
    public String getProducts(){
        String allProducts="";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            allProducts = objectMapper.writeValueAsString(products.values());
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return allProducts;
    }
    @PostMapping("/api/products")
    public void addProduct(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("price")int price, @RequestParam("imageUrl") String imageUrl){
        Product product = new Product(id,name,price,imageUrl);
        products.put(count,product);
        count++;
    }
    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }
}
