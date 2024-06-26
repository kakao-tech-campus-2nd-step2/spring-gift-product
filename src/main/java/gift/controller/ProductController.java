package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //상품 전체 조회 페이지
    @GetMapping
    public String showProductList(Model model) {
        List<Product> products = productService.selectAllProduct();
        model.addAttribute("products", products);
        return "products_list";
    }

    //상품 추가 폼 페이지
    @GetMapping("/new")
    public String createProductForm() {
        return "create_form";
    }

    //상품 추가 데이터 응답
    @PostMapping("/new")
    public String create(Product formProduct) {
        productService.addProduct(formProduct);
        return "redirect:/products";
    }

    //상품 단일 조회 기능
    @GetMapping("/{id}")
    public String showOneProduct(@PathVariable("id") Long id, Model model) {
        List<Product> products = new ArrayList<>();
        products.add(productService.selectProductById(id));
        model.addAttribute("products", products);
        return "products_list";
    }

    //상품 삭제 기능
    @GetMapping("/{id}/delete")
    public String deleteProduct(@PathVariable("id") Long id, Model model) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    //상품 수정 폼 페이지
    @GetMapping("/{id}/update")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.selectProductById(id);
        model.addAttribute("product", product);
        return "update_form";
    }

    //상품 수정 기능
    @PostMapping("/{id}")
    public String updateProduct(@PathVariable("id") Long id, Product updateProduct) {
        productService.updateProduct(id, updateProduct);
        return "redirect:/products/" + id;
    }
}
