package gift;

import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class ProductController {
    private Map<Long, Product> products = new HashMap<>();

    @GetMapping("/")
    public String listProducts(Model model) {
        Collection<Product> productCollection = products.values();
        model.addAttribute("products", productCollection);
        model.addAttribute("newProduct", new Product()); // 새 상품 객체
        model.addAttribute("product", new Product()); // 편집을 위한 빈 객체*/
        return "home"; // Thymeleaf 템플릿 이름
    }

    /*@GetMapping("/")
    @ResponseBody
    public Product getProduct(@RequestParam(value = "id") Long id) {
        return products.get(id);
    }
*/
    @PostMapping("/post")
    public String createProduct(@ModelAttribute Product newProduct){
        var product = new Product(newProduct.id, newProduct.name, newProduct.price, newProduct.imageUrl);
        products.put(product.id, product);
        System.out.println(products.get(product.id).name);
        return "redirect:/";
    }

    @PostMapping ("/update")
    public String updateProduct(@ModelAttribute Product changeProduct){
        Product changedProduct = products.get(changeProduct.id);
        changedProduct.setProduct(changeProduct);

        return "redirect:/";
    }

    @GetMapping ("/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        products.remove(id);

        return "redirect:/";
    }

}
