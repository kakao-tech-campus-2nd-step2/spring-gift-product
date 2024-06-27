package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
