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

    @GetMapping("/api/products/add")
    public String addProductForm(Model model) {
        return "addForm";
    }

    @GetMapping("/api/products/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productMap.get(id));
        return "editForm";
    }

    @PostMapping("/api/products/edit")
    public String editProduct(@RequestParam("id") Long id,
                              @RequestParam("name") String name,
                              @RequestParam("price") Long price,
                              @RequestParam("imageUrl") String imageUrl) {
        if(productMap.containsKey(id)) {
            productMap.put(id, new Product(id, name, price, imageUrl));
        }
        return "redirect:/api/products";
    }

    @PostMapping("/api/products/add")
    public String addProduct(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("price") Long price,
                             @RequestParam("imageUrl") String imageUrl) {
        if(!productMap.containsKey(id)) {
            productMap.put(id, new Product(id, name, price, imageUrl));
        }
        return "redirect:/api/products";
    }

    @PostMapping("/api/products/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {
        if(productMap.containsKey(id)) {
            productMap.remove(id);
        }
        return "redirect:/api/products";
    }
}
