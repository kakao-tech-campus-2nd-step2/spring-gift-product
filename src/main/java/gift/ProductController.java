package gift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;


@RestController
public class ProductController {
    private Map<Long, Product> products = new HashMap<>();

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

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }
}
