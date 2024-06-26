package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RequestMapping("/api/products")
@Controller
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    // 새로운 상품 등록
    @PostMapping
    public Product setProduct(@RequestBody Product product) {
        products.put(product.id(), product);
        return product;
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
    // 등록된 전체 상품 리스트 조회
    @GetMapping("/edit/{id}")
    public String moveToEditProduct(Model model) {
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
