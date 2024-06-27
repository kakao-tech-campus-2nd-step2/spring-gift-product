package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/admin")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    public ProductController(){
        products.put(814607L, new Product(814607, "아이스 카페 아메리카노 T", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"));
    }

    @GetMapping("")
    public String getProducts(Model model) {
        List<Product> productList = new ArrayList<>(products.values());
        model.addAttribute("products", productList);
        return "admin_page";
    }

    // @PostMapping("/new")
    // public String showProductForm() {
    //     return "product_form";
    // }

    // @PutMapping("/products/{id}")
    // public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    //     if (!products.containsKey(id)) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     Product updatedProduct = new Product(id, product.name(), product.price(), product.imageUrl());
    //     products.put(id, updatedProduct);
    //     return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    // }

    // @DeleteMapping("/products/{id}")
    // public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    //     if (!products.containsKey(id)) {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    //     products.remove(id);
    //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
}