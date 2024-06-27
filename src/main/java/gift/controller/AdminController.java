package gift.controller;

import gift.controller.req.ProductRequest;
import gift.controller.res.ProductResponse;
import gift.model.Product;
import gift.model.ProductRepository;
import java.nio.file.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;

    @Autowired
    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public String listProducts(Model model) {
        List<Product> products = productRepository.findAll().stream().filter(product -> !product.isDeleted()).toList();
        model.addAttribute("products", products);
        return "admin/list";
    }

    @GetMapping("/products/{id}")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.find(id);
        if (product == null || product.isDeleted()) {
            throw new IllegalArgumentException();
        }
        model.addAttribute("product", product);
        return "admin/edit";
    }

    @PostMapping("/products")
    public String productSave(@RequestBody ProductRequest newProduct){
        productRepository.save(newProduct.toModel());

        return "admin/list";
    }

    @PatchMapping("/products/{id}")
    public String productModify(@PathVariable("id") Long id, @RequestBody ProductRequest modifyProduct){

        Product findedProduct = productRepository.find(id);
        if(findedProduct == null || findedProduct.isDeleted()){
            throw new IllegalArgumentException();
        }
        productRepository.save(modifyProduct.toModel(id));
        return "admin/list";
    }

    @DeleteMapping("/products/{id}")
    public String productDelete(@PathVariable("id") Long id){
        Product findedProduct = productRepository.find(id);
        if(findedProduct == null){
            throw new IllegalArgumentException();
        }

        findedProduct.delete();
        productRepository.save(findedProduct);
        return "admin/list";
    }

}
