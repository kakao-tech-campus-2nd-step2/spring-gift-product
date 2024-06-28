package gift.domain.product.controller;

import gift.domain.product.dao.ProductDao;
import gift.domain.product.dto.ProductDto;
import gift.domain.product.entity.Product;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductDao productDao;
    private final Map<Long, Product> productRepository = new ConcurrentHashMap<>();
    private final AtomicLong key = new AtomicLong(1);

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/new")
    public String renderingNewForm() {
        return "new-product";
    }

    @GetMapping("/edit/{productId}")
    public String renderingEditForm(@PathVariable long productId, Model model) {
        Optional<Product> product = productDao.findById(productId);

        if (product.isEmpty()) {
            return "error";
        }
        model.addAttribute("product", product.get());

        return "edit-product";
    }

    @PostMapping
    public String create(@ModelAttribute ProductDto productDto) {
        Product product = productDto.toProduct();

        Product savedProduct = productDao.insert(product);

        return "redirect:/products/" + savedProduct.getId();
    }

    @GetMapping
    public String readAll(Model model) {
        List<Product> productList = productDao.findAll();
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/{productId}")
    public String readById(@PathVariable long productId, Model model) {
        Optional<Product> product = productDao.findById(productId);

        if (product.isEmpty()) {
            return "error";
        }
        model.addAttribute("product", product.get());

        return "product";
    }

    @PutMapping("/{productId}")
    public String update(@PathVariable long productId, @ModelAttribute ProductDto productDto) {
        Product product = productDto.toProduct();
        product.setId(productId);

        Optional<Product> updatedProduct = productDao.update(product);

        return updatedProduct.map(value -> "redirect:/products/" + value.getId()).orElse("error");

    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> delete(@PathVariable long productId) {
        Product product = productRepository.get(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.remove(productId);

        return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
    }
}
