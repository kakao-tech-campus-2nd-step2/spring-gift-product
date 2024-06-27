package gift.controller;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductViewController {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long sequence = 0L;

    @GetMapping("/step2/products")
    public String getAllProducts(Model model) {
        List<Product> productsList = products.values().stream().toList();
        model.addAttribute("products", productsList);
        return "products";
    }

    @GetMapping("/step2/products/add")
    public String addForm() {
        return "addForm";
    }

    @PostMapping("/step2/products/add")
    public String addProduct(@RequestParam String name, @RequestParam int price,
        @RequestParam String imageUrl) {
        Long id = ++sequence;
        Product product = new Product(id, name, price, imageUrl);
        products.put(id, product);
        return "redirect:/step2/products";
    }

    @GetMapping("/step2/products/edit")
    public String editForm(@RequestParam Long id, Model model) {
        model.addAttribute("product", products.get(id));
        return "editForm";
    }

    @PostMapping("/step2/products/edit")
    public String editProduct(@RequestParam Long id, @RequestParam String name,
        @RequestParam int price, @RequestParam String imageUrl) {
        Product revisedProduct = products.get(id);
        revisedProduct.setName(name);
        revisedProduct.setPrice(price);
        revisedProduct.setImageUrl(imageUrl);

        products.put(id, revisedProduct);
        return "redirect:/step2/products";
    }

    @PostMapping("/step2/products/delete")
    public String deleteProduct(@RequestParam("id") Long id) {
        products.remove(id);
        return "redirect:/step2/products";
    }

}
