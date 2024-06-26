package gift;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    private final Map<Long, Product> productMap = new HashMap<>();

    @GetMapping("/api/products")
    public String getProducts(Model model) {
        List<Product> products = productMap.values()
                .stream()
                .toList();
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/api/products")
    public String addProduct(@RequestBody Product newProduct) {
        if(productMap.containsKey(newProduct.id())) {
            return "이미 상품이 등록되어있습니다.";
        }
        productMap.put(newProduct.id(), newProduct);
        return "상품 등록이 완료되었습니다.";
    }

    @PutMapping("/api/products/{id}")
    public String updateProduct(@RequestBody Product updatedProduct, @PathVariable(name = "id") Long id) {
        if(productMap.containsKey(id)) {
            productMap.put(id, updatedProduct);
            return "상품 정보 수정이 완료되었습니다.";
        }
        return "해당 상품이 존재하지 않습니다.";
    }

    @DeleteMapping("/api/products/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        if(productMap.containsKey(id)) {
            productMap.remove(id);
            return "상품 삭제가 완료되었습니다.";
        }
        return "해당 상품이 존재하지 않습니다";
    }
}
