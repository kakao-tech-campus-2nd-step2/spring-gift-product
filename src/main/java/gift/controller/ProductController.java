package gift.controller;

import gift.entity.Product;
import gift.service.ProductService;
import jakarta.validation.Valid;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/products")
public class ProductController {
  @Autowired
  private ProductService productService;

  @GetMapping
  public String showAllProducts(Model model) {
    model.addAttribute("products", productService.findAll());
    return "product-list";
  }

  @GetMapping("/add")
  public String showAddProductForm(Model model) {
    model.addAttribute("product", new Product());
    return "product-form";
  }

  @GetMapping("/edit/{id}")
  public String showEditProductForm(@PathVariable("id") Long id, Model model) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      model.addAttribute("product", product.get());
      return "product-form";
    } else {
      return "redirect:/products";
    }
  }

  @PostMapping("/save")
  public String saveProduct(@Valid @ModelAttribute Product product, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()) {
      return "product-form";
    }
    productService.save(product);
    return "redirect:/products";
  }

  @GetMapping("/delete/{id}")
  public String deleteProduct(@PathVariable("id") Long id) {
    productService.deleteById(id);
    return "redirect:/products";
  }

  @DeleteMapping("/{id}")
  @ResponseBody
  public String deleteProductAjax(@PathVariable("id") Long id) {
    productService.deleteById(id);
    return "success";
  }
}
