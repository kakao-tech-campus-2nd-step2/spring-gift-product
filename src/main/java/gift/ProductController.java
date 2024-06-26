package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    ProductMemoryDB productDB = ProductMemoryDB.getInstance();

    //상품 보여주기
    @GetMapping("/")
    public String getProducts(Model model) {

        model.addAttribute("products", productDB.getProducts());
        System.out.println("hello");
        return "version-SSR/index.html";
    }

    //상품 추가하기
    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("product", new Product()); // Add an empty Product object for the form
        return "version-SSR/add-form.html";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        productDB.addProduct(product);
        return "redirect:/";
    }
    //상품 삭제하기
    // POST 요청으로 선택된 상품들 삭제
    @PostMapping("/deleteSelected")
    public String deleteSelectedProduct(@RequestParam("productIds") List<Long> productIds) {
        System.out.println("선택");
        productDB.removeProducts(productIds);
        return "redirect:/";
    }
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") Long productId ){
        productDB.removeProduct(productId);
        System.out.println(productId);
        return "redirect:/";
    }

}
