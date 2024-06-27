package gift.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @PostMapping("/manager/product/add")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes){
        System.out.println("add");
        products.put(product.id(), product);
        redirectAttributes.addAttribute("id", product.id());
        System.out.println(product.id());
        return "redirect:/manager/product/{id}";
    }

    @PostMapping("/manager/product/update/{id}")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes){
        System.out.println("update");
        products.put(product.id(), product);
        redirectAttributes.addAttribute("id", product.id());
        return "redirect:/manager/product/{id}";
    }

    @PostMapping("/manager/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        System.out.println("delete");
        Product product = products.get(id);
        if(product != null){
            products.remove(id);
        }
        return "redirect:/manager/products";
    }

    @GetMapping("/manager/products")
    public String getProductsView(Model model){
        model.addAttribute("products", products.values());
        for (Product value : products.values()) {
            System.out.println(value.name());

        }
        return "ManageProduct";
    }

    @GetMapping("/manager/product/add")
    public String addProductView(Model model){
        model.addAttribute("product", new Product(null,null,null,null));
        return "AddOrUpdateProduct";
    }

    @GetMapping("/manager/product/update/{id}")
    public String updateProductView(@PathVariable Long id, Model model){
        model.addAttribute("product", products.get(id));
        return "AddOrUpdateProduct";
    }

    @GetMapping("/manager/product/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", product);
        return "ProductInfo";
    }
}
