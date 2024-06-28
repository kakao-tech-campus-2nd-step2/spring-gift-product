package gift.controller;

import gift.dao.ProductDao;
import gift.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/list")
public class AdminController {
    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao){
        this.productDao = productDao;
    }

    @GetMapping
    public String getAllProducts(Model model){
        List<Product> productList = productDao.selectAllProduct();
        model.addAttribute("productList", productList);
        return "list";
    }

    @GetMapping("/add")
    public String addProductForm(Model model){
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product){
        productDao.insertProduct(product);
        return "redirect:/admin/list";
    }


}
