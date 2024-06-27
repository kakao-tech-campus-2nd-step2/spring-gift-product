package gift.controller;

import gift.Dao.ProductDao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import gift.model.Product;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    ProductDao productDao = new ProductDao();

    @GetMapping("")
    public List<Product> getAllProducts(){
        return productDao.selectAllProduct();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable("id") Long id){
        return productDao.selectProductById(id);
    }

    @PostMapping()
    public void addProduct(@RequestBody Product product){
        productDao.InsertProduct(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@RequestBody Product product, @PathVariable("id") Long id){
        productDao.updateProductbyId(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        productDao.deleteProductById(id);
    }

}
