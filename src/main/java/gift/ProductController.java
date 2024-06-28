package gift;

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
    private final Map<Long, Product> products = new HashMap<>();

    // 새로운 상품 등록(데이터 베이스 연동)
    @PostMapping
    public ResponseEntity<String> setProduct(@RequestBody Product product) {
        if (!products.containsKey(product.id())) {
            productDao.insertProduct(product);
            return ResponseEntity.ok("Add");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("already");
        }
    }
    // 등록된 상품 업데이트
    @PutMapping("/{id}")
    public Object updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if(products.containsKey(id)){
            products.put(id, product);
            return product;
        }
        return "Update failed";
    }
    // 등록된 상품 수정 페이지 표시
    @GetMapping("/edit/{id}")
    public String moveToEditProduct(@PathVariable Long id, Model model) {
        model.addAttribute("product", products.get(id));
        return "editProduct";
    }
    // 등록된 전체 상품 리스트 조회
    @GetMapping
    public String getproductList(Model model) {
        model.addAttribute("products", products.values());
        return "productManage";
    }
    // 상품 추가 페이지 표시
    @GetMapping("/add")
    public String movtoAddProduct(Model model) {
        return "addProduct";
    }
    // 등록된 상품 삭제
    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable Long id){
        if(products.containsKey(id)) {
            products.remove(id);
        }
        return "productManage";
    }
}
