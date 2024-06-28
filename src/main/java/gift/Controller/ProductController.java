package gift.Controller;

import org.springframework.ui.Model;
import gift.Model.Product;
import gift.Model.RequestProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/api")
public class ProductController {
    private final Map<Long, Product> products = new HashMap<Long, Product>();

    @GetMapping("/products")
    public String getProduct(Model model) {
        List<Product> list = new LinkedList<>(products.values());
        model.addAttribute("products", list);
        return "products";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new RequestProduct("", 0, ""));
        return "new-product";
    }

    @PostMapping("/products")
    public String newProduct(@ModelAttribute RequestProduct requestProduct) {
        Product product = new Product(requestProduct.name(), requestProduct.price(), requestProduct.imageUrl());
        products.put(product.getId(), product);
        return "redirect:/api/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable("id") Long id, Model model) {
        Product product = products.get(id);
        model.addAttribute("product", new RequestProduct(product.getName(), product.getPrice(), product.getImageUrl()));
        model.addAttribute("id", id);
            return "edit-product";
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, @ModelAttribute RequestProduct requestProduct) {
        Product product = products.get(id);
        product.setName(requestProduct.name());
        product.setPrice(requestProduct.price());
        product.setImageUrl(requestProduct.imageUrl());

        return "redirect:/api/products";
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteMethod(@PathVariable("id") Long id) {
        boolean isExist = products.containsKey(id);
        if (isExist) {
            products.remove(id);
            return ResponseEntity.status(HttpStatus.OK).body("Delete Complete.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not Exist");

    }

}
