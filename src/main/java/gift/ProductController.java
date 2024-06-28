package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    //Product 클래스를 저장하는 해시맵
    private final ProductDAO productDAO;
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
        productDAO.create();
        productDAO.insert(new Product(1, "test", 1, "test"));
    }

    @GetMapping("api/products")
    public List<Product> getAllProducts() {
        return productDAO.selectAll();
    }

    @GetMapping("/api/products/{id}")
    public Product getProduct(@PathVariable long id) {
        return productDAO.select(id);
    }

    @PostMapping("/api/products")
    public HttpStatus addProduct(@RequestBody Product product) {
        //json 입력을 받아 product 객체 만들고 해시맵에 저장
        productDAO.insert(product);
        return HttpStatus.CREATED;

    }

    @DeleteMapping("/api/products/{id}")
    public HttpStatus removeProduct(@PathVariable long id) {
        // 입력된 id에 해당하는 해시맵 내 객체 삭제
        try {
            productDAO.delete(id);
            return HttpStatus.NO_CONTENT;
        }
        catch (Exception e) {
            return HttpStatus.NOT_FOUND;
        }
    }

    @PutMapping("/api/products/{id}")
    public HttpStatus updateProduct(@PathVariable long id, @RequestBody Product product) {
        // 입력된 id에 해당하는 해시맵 내 객체 수정
        productDAO.update(id, product);
        return HttpStatus.OK;
    }
}