package gift.Controller;

import gift.Repository.Products;
import gift.domain.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final Products products;
    public ProductController(){
        this.products=new Products();
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(products.findAll());
    }
    @PostMapping
    public ResponseEntity<Product> addProducts(@RequestBody Product product){
        if(products.addProduct(product)){
            return new ResponseEntity<>(product,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> modifyProducts(@PathVariable("id") long id,@RequestBody Product product){
        if(products.updateProduct(product)){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProducts(@PathVariable("id") long id){
        if(products.deleteProduct(id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
