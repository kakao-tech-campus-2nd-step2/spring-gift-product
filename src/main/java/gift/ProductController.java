package gift;

import org.springframework.web.bind.annotation.*;

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
}
