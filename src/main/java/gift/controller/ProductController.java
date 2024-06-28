package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    public final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String retreiveAllProducts(Model model) {
        if (!productService.isProductsRepositoryEmpty()) {
            model.addAttribute("productDto", productService.getAllProducts().values());
        }
        return "getproducts";
    }

    @GetMapping("/product/{id}")
    public String retreiveProduct(@PathVariable(name = "id") Long id, Model model) {
        var productData = productService.getProduct(id);
        if (productData != null) {
            return "Empty Products";
        }
        if (!productService.getAllProducts().containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build().toString();
        }
        var product = productService.getAllProducts().get(id);
        model.addAttribute("productDto", product);
        return "getproducts";
    }

    @GetMapping("/product/add/form")
    public String registerProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "addproductform";
    }

    @PostMapping("/product/add")
    public String registerProduct(@ModelAttribute("productDto") Product product,
        HttpServletResponse response) {
        var newProduct = new Product(product.getId(), product.getName(), product.getPrice(),
            product.getImageUrl());
        if (handleBadAttribute(response, product)) {
            return null;
        }
        if (handleAlreadyExist(response, product)) {
            return null;
        }
        productService.addProduct(newProduct);
        return "redirect:/";
    }

    @GetMapping("/product/update/form")
    public String updateProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "updateproductform";
    }


    @PostMapping("/product/update")
    public String updateProductsName(@ModelAttribute(name = "productDto") Product product,
        HttpServletResponse httpServletResponse
    ) {
        if (handleBadAttribute(httpServletResponse, product)) {
            return null;
        }
        if (handleNonExistProduct(httpServletResponse, product)) {
            return null;
        }
        productService.updateProductDetail(product);
        return "redirect:/";
    }


    @GetMapping("/product/delete/form")
    public String deleteProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "deleteproduct";
    }

    @GetMapping("/product/delete")
    public String deleteProduct(@ModelAttribute(name = "productDto") Product product,
        HttpServletResponse httpServletResponse) {
        if (handleNonExistProduct(httpServletResponse, product)) {
            return null;
        }
        productService.deleteProductById(product.getId());
        return "redirect:/";
    }

    private boolean handleNonExistProduct(HttpServletResponse httpServletResponse,
        Product product) {
        if (!productService.isExistProduct(product)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                httpServletResponse.sendRedirect("/");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private boolean handleAlreadyExist(HttpServletResponse response, Product product) {
        if (productService.isExistProduct(product)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private boolean handleBadAttribute(HttpServletResponse response, Product newProduct) {
        if (newProduct.getId() == null || newProduct.getId() < 0 || newProduct.getName().isEmpty()
            || newProduct.getPrice() < 0
            || newProduct.getImageUrl().isEmpty()) {
            // BAD_REQUEST임을 알리는 동시에 다시 리다이렉트 시킬 수 있다.
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}


