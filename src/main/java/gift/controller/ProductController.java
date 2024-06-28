package gift.controller;

import gift.dto.Product;
import gift.db.ProductDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    //@Qualifier("MEMORY DATABASE") //memoryDB(Map<Long, Product>>) 원하면 주석 풀고 아래 H2를 주석화 시킬 것
    @Qualifier("H2 DATABASE")
    ProductDB productDB;

    //상품 보여주기
    @GetMapping("/")
    public String getProducts(Model model) {
        model.addAttribute("products", productDB.getProducts());
        return "version-SSR/index";
    }

    //상품 추가하기
    @GetMapping("/add")
    public String getAddForm(Model model) {
        model.addAttribute("product", new Product()); // Add an empty Product object for the form
        return "version-SSR/add-form";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        try {
            productDB.addProduct(product);
            return "redirect:/";
        } catch (Exception e) {
            return "version-SSR/add-error";
        }
    }

    //상품 삭제하기
    @PostMapping("/deleteSelected")
    public String deleteSelectedProduct(@RequestParam("productIds") List<Long> productIds) {
        productDB.removeProducts(productIds);
        return "redirect:/";
    }

    @PostMapping("/delete")
    public String deleteProduct(@RequestParam("productId") Long productId) {
        productDB.removeProduct(productId);
        return "redirect:/";
    }

    //상품 수정하기
    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productDB.getProduct(id)); // Add an empty Product object for the form
        return "version-SSR/edit-form";
    }

    @PostMapping("/edit")
    public String getEditForm(Product product) {
        try {
            productDB.editProduct(product);
            return "redirect:/";
        } catch (Exception e) {
            return "version-SSR/edit-error";
        }
    }

}
