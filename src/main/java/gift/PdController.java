package gift;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/products")
public class PdController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("")
    public ModelAndView getAllProducts() {
        ModelAndView modelAndView = new ModelAndView("api/products");
        modelAndView.addObject("products", products.values());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return products.get(id);
    }

    @PostMapping("")
    public String putProduct(@RequestBody Product product){
        products.put(product.id(), product);
        return "redirect:/api/products";
    }
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        if (products.containsKey(id)) {
            products.put(id, product);
        }
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }

    @PostMapping("/delete")
    public void deleteSelectedProducts(@RequestBody List<Long> productIds) {
        productIds.forEach(products::remove);
    }
}
