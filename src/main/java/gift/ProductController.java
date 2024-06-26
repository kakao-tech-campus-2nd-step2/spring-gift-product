package gift;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.*;


@RestController
public class ProductController {
    private Map<Long, Product> products = new HashMap<>();
    private static Long count = 1L;

    @GetMapping("/admin/products")
    public ModelAndView adminProducts(Model model){
        model.addAttribute("products", products.values());
        return new ModelAndView("admin/products");
    }
    @GetMapping("/admin/add")
    public ModelAndView adminProductsAdd(Model model){
        return new ModelAndView("admin/add");
    }
    @GetMapping("/admin/modify")
    public ModelAndView adminProductsModify(Model model){
        return new ModelAndView("admin/modify");
    }
    @GetMapping("/admin/delete")
    public ModelAndView adminProductsDelete(Model model){
        return new ModelAndView("admin/delete");
    }

    @GetMapping("/api/products")
    public String getProducts() {
        String allProducts = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            allProducts = objectMapper.writeValueAsString(products.values());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return allProducts;
    }

    @PostMapping("/api/products/add")
    public void addProduct(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl) {
        Product product = new Product(id, name, price, imageUrl);
        products.put(count, product);
        count++;
    }

    @PostMapping("/api/products/delete")
    public void deleteProduct(@RequestParam("id") int id) {
        Iterator<Map.Entry<Long, Product>> iterator = products.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Product> entry = iterator.next();
            if (entry.getValue().id == id) {
                iterator.remove();
            }
        }
    }

    @PostMapping("/api/products/modify")
    public void modifyProduct(@RequestParam("id") int id, @RequestParam("name") String name, @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl) {
        Iterator<Map.Entry<Long, Product>> iterator = products.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Product> entry = iterator.next();
            if (entry.getValue().id == id) {
                entry.getValue().name = name;
                entry.getValue().price = price;
                entry.getValue().imageUrl = imageUrl;
            }
        }
    }

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }

}

