package gift;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/products")
@Controller
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }
    //private final Map<Long, Product> products = new HashMap<>();

    // 새로운 상품 등록(DB 연동)
    @PostMapping
    public ResponseEntity<String> setProduct(@RequestBody Product product) {
        try {
            productDao.insertProduct(product);
            return ResponseEntity.ok("Add");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("already");
        }
    }
    // 등록된 상품 업데이트(DB 연동)
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody Product product) {
        productDao.updateProduct(product);
        return "Update Success";
    }
    // 등록된 상품 수정 페이지 표시(DB 연동)
    @GetMapping("/edit/{id}")
    public String moveToEditProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", productDao.selectProduct(id));
        return "editProduct";
    }
    // 등록된 전체 상품 리스트 조회(DB 연동)
    @GetMapping
    public String getproductList(Model model) {
        model.addAttribute("products", productDao.selectAllProducts());
        return "productManage";
    }
    // 상품 추가 페이지 표시
    @GetMapping("/add")
    public String movtoAddProduct(Model model) {
        return "addProduct";
    }
    // 등록된 상품 삭제(DB 연동)
    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable Long id){
        productDao.deleteProduct(id);
        return "productManage";
    }
}
