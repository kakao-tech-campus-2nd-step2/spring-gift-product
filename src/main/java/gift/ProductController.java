package gift;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<Long, Product>();

    @GetMapping("/products") //조회
    public List<Product> getMethod() {
        List<Product> list = new LinkedList<Product>();
        Iterator<Long> keys = products.keySet().iterator();
        while (keys.hasNext()) {
            Long key = keys.next();
            Product data = products.get(key);
            list.add(data);
        }
        return list;
    }

    @GetMapping("/products/{id}") //조회
    public Product single_getMethod(@PathVariable("id") Long id) {
        Product data = products.get(id);

        return data;
    }

    @PostMapping ("/products")
    public void postMethod(@RequestBody RequestProduct requestProduct){
        Product product  = new Product(requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
        products.put(product.getId(), product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> putMethod(@RequestBody RequestProduct requestProduct, @PathVariable("id") Long id) {
        boolean isExist = products.containsKey(id);
        if(isExist){
            Product product = new Product(requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
            Product original = products.get(id);

            original.setName(product.getName());
            original.setPrice(product.getPrice());
            original.setImageUrl(product.getImageUrl());

            return ResponseEntity.status(HttpStatus.OK).body("Update Complete.");
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");

    }

}
