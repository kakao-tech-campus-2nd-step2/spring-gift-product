package gift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public String getProducts(){
        String allProducts="";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            for (Product p : products.values()) {
                allProducts = allProducts.concat(objectMapper.writeValueAsString(p));
                allProducts = allProducts.concat("\n");
            }
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
        return allProducts;
    }

}
