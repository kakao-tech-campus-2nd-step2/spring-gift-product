package gift.Controller;

import gift.Model.Product;
import gift.Repository.ProductRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/products")
public class AdminController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productRepository.checkProductsAll());
        return "product_list";
    }

    @GetMapping("/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product(0, "", 0, ""));
        return "add_product_form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product, Model model) {
        if (productRepository.checkProductsById(product.id()) != null) {
            model.addAttribute("error", "존재하는 ID 입니다.");
            model.addAttribute("product", product);
            return "add_product_form";
        }
        productRepository.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditProductForm(@PathVariable("id") long id, Model model) {
        Product product = productRepository.checkProductsById(id);
        model.addAttribute("product", product);
        return "edit_product_form";
    }

    @PutMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, @ModelAttribute Product updatedProduct, Model model) {
        if (id != updatedProduct.id() && productRepository.checkProductsById(updatedProduct.id()) != null) {
            model.addAttribute("error", "존재하는 ID 입니다.");
            model.addAttribute("product", productRepository.checkProductsById(id));
            return "edit_product_form";
        }
        productRepository.updateProduct(updatedProduct, id);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productRepository.deleteProduct(id);
        return "redirect:/admin/products";
    }
}