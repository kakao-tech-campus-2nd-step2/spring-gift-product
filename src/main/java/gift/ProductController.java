package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private Map<Long, Product> products = new HashMap<>();

    @GetMapping("/get")
    public Product getProduct(@RequestParam(value = "id") Long id) {
        return products.get(id);
    }

    @PostMapping("/post")
    public ResponseEntity createProduct(@RequestBody Product newProduct){
        var product = new Product(newProduct.id, newProduct.name, newProduct.price, newProduct.imageUrl);
        products.put(product.id, product);
        System.out.println(products.get(product.id).name);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/put")
    public ResponseEntity updateProduct(@RequestBody Product changeProduct){
        Product changedProduct = products.get(changeProduct.id);
        changedProduct.setProduct(changeProduct);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteProductt(@RequestParam(value = "id") Long id){
        products.remove(id);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
