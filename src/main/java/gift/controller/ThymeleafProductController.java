package gift.controller;

import gift.database.ProductDatabase;
import gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ThymeleafProductController {

    @Autowired
    private ProductDatabase productDatabase;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productDatabase.findAll();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/new")
    public String createProductForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }

    @PostMapping
    public String addProduct(@ModelAttribute("product") Product product) {
        productDatabase.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productDatabase.findById(id);
        if (product == null) {
            return "redirect:/products";
        }
        model.addAttribute("product", product);
        return "edit-product";
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute Product product) {
        Product existingProduct = productDatabase.findById(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());
            productDatabase.save(existingProduct);
        }
        return "redirect:/products";
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productDatabase.deleteById(id);
        return "redirect:/products";
    }

}
