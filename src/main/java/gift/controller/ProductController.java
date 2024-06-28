package gift.controller;

import gift.dto.ProductRequest;
import gift.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", products.values());
        return "index";
    }

    @GetMapping("/{id}/edit")
    public String getProduct(@PathVariable long id, Model model) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        model.addAttribute("product", products.get(id));
        return "editForm";
    }

    @GetMapping("/new")
    public String addProductForm(Model model) {
        model.addAttribute("productRequest", new ProductRequest("", 0, ""));
        return "addForm";
    }

    @PostMapping
    public String addProduct(@ModelAttribute ProductRequest productRequest) {
        long id = idGenerator.incrementAndGet();
        Product product = new Product(id, productRequest.name(), productRequest.price(), productRequest.imageUrl());
        products.put(id, product);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute ProductRequest productRequest) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        Product product = new Product(id, productRequest.name(), productRequest.price(), productRequest.imageUrl());
        products.put(id, product);
        return "redirect:/products";
    }

    @PostMapping("/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {
        if (!products.containsKey(id)) {
            throw new IllegalArgumentException("Product with id " + id + " not found");
        }
        products.remove(id);
        return "redirect:/products";
    }

    @PostMapping("/delete-batch")
    @ResponseBody
    public String deleteBatch(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("ids");
        for (Long id : ids) {
            products.remove(id);
        }
        return "Success";
    }
}
