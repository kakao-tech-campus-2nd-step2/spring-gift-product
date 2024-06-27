package gift;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    private ProductDAO productDAO = new ProductDAO();

    @GetMapping("/products")
    public ProductRecord[] getAllProducts() {
        return productDAO.getAllRecords();
    }

    @PostMapping("/products")
    public ProductRecord addProduct(@RequestBody ProductRecord product) {
        return productDAO.addNewRecord(product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        productDAO.deleteRecord(id);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductRecord> updateProduct(@PathVariable int id, @RequestBody ProductRecord product) {
        ProductRecord response;
        try {
            response = productDAO.replaceRecord(id, product);

            return ResponseEntity.ok(response);
        } catch (NoSuchElementException e) {
            response =  productDAO.addNewRecord(product, id);

            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/products/"+ id)
                    .build()
                    .toUri();

            return ResponseEntity.created(location).body(response);
        }
    }
}
