package gift.controller;

import gift.dto.CreateProduct;
import gift.dto.ProductDTO;
import gift.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class JdbcProductController {
    private final ProductDao productDao;
    public JdbcProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @PostMapping("/table/jdbc")
    public void createProductTable() {
            productDao.createProductTable();
    }
    //add
    @PostMapping("/product/jdbc")
    public void createProduct(@ModelAttribute CreateProduct.Request request){
        productDao.insertProduct(new ProductDTO(request.getId(),request.getName(), request.getPrice(), request.getImageUrl()));
    }
    //get one by id
    @GetMapping("/product/jdbc/{id}")
    public ProductDTO getProductById(@PathVariable("id") long id){
        return productDao.selectProduct(id);
    }



}

