package gift.controller;

import gift.model.Product;
import gift.model.ProductRequest;
import gift.model.ProductDao;
import gift.model.ProductResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/api/product")
    public String registerProductForm() {
        return "addProduct";
    }

    @PostMapping("/api/product")
    public String registerProduct(ProductRequest productRequest) {
        Product product = productDao.save(productRequest);
        return "redirect:/api/products";
    }

    @GetMapping("/api/products")
    public String getAllProducts(Model model) {
        List<ProductResponse> productList = productDao.findAll()
            .stream().map(Product::toDTO).toList();
        model.addAttribute("products", productList);
        return "productList";
    }

    @GetMapping("/api/product/{id}")
    public ProductResponse getProduct(@PathVariable("id") Long id) {
        Product product = productDao.findById(id);
        return product.toDTO();
    }

    @PutMapping("/api/product/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest productRequest) {
        Product product = productDao.update(id, productRequest);
        return product.toDTO();
    }

    @DeleteMapping("/api/product/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productDao.delete(id);
        return "redirect:/api/products";
    }
}
