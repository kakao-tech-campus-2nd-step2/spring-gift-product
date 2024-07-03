package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    final String apipath = "/api/products";

    @Autowired
    private final ProductDAO productDAO;

    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
        productDAO.create();
        productDAO.insert(new Product(1, "test", 1, "test"));
    }

    @GetMapping(apipath)
    public List<Product> getAllProducts() {
        return productDAO.selectAll();
    }

    @GetMapping(apipath + "/{id}")
    public Product getProduct(@PathVariable long id) {
        return productDAO.select(id);
    }

    @PostMapping(apipath)
    public HttpStatus addProduct(@RequestBody Product product) {
        productDAO.insert(product);
        return HttpStatus.CREATED;

    }

    @DeleteMapping(apipath + "/{id}")
    public HttpStatus removeProduct(@PathVariable long id) {
        try {
            productDAO.delete(id);
            return HttpStatus.NO_CONTENT;
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping(apipath + "/{id}")
    public HttpStatus updateProduct(@PathVariable long id, @RequestBody Product product) {
        productDAO.update(id, product);
        return HttpStatus.OK;
    }
}