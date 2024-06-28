package gift.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/manager/product/add")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes){
        System.out.println("add");
        Product product1 = productRepository.insertProduct(product);
        redirectAttributes.addAttribute("id", product1.id());
        System.out.println(product1.id());
        return "redirect:/manager/product/{id}";
    }

    @PostMapping("/manager/product/update/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute Product product, RedirectAttributes redirectAttributes){
        System.out.println("update");
        productRepository.updateProduct(id, product);
        redirectAttributes.addAttribute("id", id);
        return "redirect:/manager/product/{id}";
    }

    @PostMapping("/manager/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        System.out.println("delete");
        Product product = productRepository.selectProduct(id);
        if(product != null){
            productRepository.deleteProduct(id);
        }
        return "redirect:/manager/products";
    }

    @GetMapping("/manager/products")
    public String getProductsView(Model model){
        model.addAttribute("products", productRepository.selectProducts());
        return "ManageProduct";
    }

    @GetMapping("/manager/product/add")
    public String addProductView(Model model){
        model.addAttribute("product", new Product(null,null,null,null));
        return "AddOrUpdateProduct";
    }

    @GetMapping("/manager/product/update/{id}")
    public String updateProductView(@PathVariable Long id, Model model){
        model.addAttribute("product", productRepository.selectProduct(id));
        return "AddOrUpdateProduct";
    }

    @GetMapping("/manager/product/{id}")
    public String getProduct(@PathVariable long id, Model model) {
        Product product = productRepository.selectProduct(id);
        model.addAttribute("product", product);
        return "ProductInfo";
    }
}

