package gift;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    final String apipath = "/api/products";

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(apipath)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(apipath + "/{id}")
    public Product getProduct(@PathVariable long id) {
        return productService.getProductById(id);
    }

    @PostMapping(apipath)
    public HttpStatus addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return HttpStatus.CREATED;

    }

    @DeleteMapping(apipath + "/{id}")
    public HttpStatus removeProduct(@PathVariable long id) {
        try {
            productService.deleteProduct(id);
            return HttpStatus.NO_CONTENT;
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping(apipath + "/{id}")
    public HttpStatus updateProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(id, product);
        return HttpStatus.OK;
    }
}