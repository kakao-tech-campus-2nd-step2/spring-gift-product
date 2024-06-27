package gift.Controller;

import gift.Repository.JdbcProducts;
import gift.Repository.Products;
import gift.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jdbc/products")
public class JdbcAdminController {

    private final JdbcProducts jdbcProducts;

    public JdbcAdminController(JdbcProducts jdbcProducts) {
        this.jdbcProducts=jdbcProducts;
    }
    //전체 상품목록
    @GetMapping
    public String viewProducts(Model model) {
        model.addAttribute("products", jdbcProducts.findAll());
        return "products-list";
    }
    //상품 추가폼 끌어오기
    @GetMapping("/add")
    public String addProductsForm(Model model) {
        model.addAttribute("product",new Product());
        return "products-form";
    }
    //상품추가 Post
    @PostMapping("/add")
    public String addProduct(Model model,@ModelAttribute Product product){
        if(jdbcProducts.addProduct(product)){
            return "redirect:/products";
        }
        model.addAttribute("error","이미존재하는 상품 id");
        return "products-form";
    }

    //상품업데이트
    @GetMapping("/update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Product product=jdbcProducts.getProduct(id);
        if(product!=null){
            model.addAttribute("product",product);
            return "update-product";
        }
        return "redirect:/product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model, @ModelAttribute Product product){
        if(jdbcProducts.updateProduct(product)){
            return "redirect:/products";
        }
        model.addAttribute("error","존재하지 않는 상품 id");
        return "update-product";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        jdbcProducts.deleteProduct(id);
        return "redirect:/products";
    }

}
