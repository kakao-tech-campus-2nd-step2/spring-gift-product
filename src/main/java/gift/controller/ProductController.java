package gift.controller;

import gift.dto.Product;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/api/products")
    public String getAllProducts(Model model) {
        if (!products.isEmpty()) {
            model.addAttribute("productDto", products.values());
        }
        return "getproducts";
    }

    @GetMapping("/api/products/{id}")
    public String getProduct(@PathVariable(name = "id") Long id, Model model) {
        if (products.isEmpty()) {
            return "Empty Products";
        }
        if (!products.containsKey(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build().toString();
        }
        var product = products.get(id);
        model.addAttribute("productDto", product);
        return "getproducts";
    }

    @GetMapping("/api/products/add/form")
    public String addProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "addproductform";
    }

    @PostMapping("/api/products/add")
    public String addProduct(@ModelAttribute("productDto") Product product,
        HttpServletResponse response) {
        var newProduct = new Product(product.getId(), product.getName(), product.getPrice(),
            product.getImageUrl());
        if (handleBadAttribute(response, product)) {
            return null;
        }
        if (handleAlreadyExist(products.containsKey(product.getId()), response)) {
            return null;
        }
        products.put(newProduct.getId(), newProduct);
        return "redirect:/api/products";
    }

    @GetMapping("/api/products/update/form")
    public String updateProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "updateproductform";
    }


    @PostMapping("/api/products/update")
    public String updateProductsName(@ModelAttribute(name = "productDto") Product product,HttpServletResponse httpServletResponse
        ) {
        if (handleBadAttribute(httpServletResponse, product)) {
            return null;
        }
        if (handleNonExistProduct(product, httpServletResponse)) {
            return null;
        }
        var updatedProduct = products.get(product.getId());
        updatedProduct.setName(product.getName());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setImageUrl(product.getImageUrl());
        return "redirect:/api/products";
    }


    @GetMapping("/api/products/delete/form")
    public String deleteProductForm(Model model) {
        model.addAttribute("productDto", new Product());
        return "deleteproduct";
    }

    @GetMapping("/api/products/delete")
    public String deleteProduct(@ModelAttribute(name = "productDto") Product product,
        HttpServletResponse httpServletResponse) {
        if (handleNonExistProduct(product, httpServletResponse)) {
            return null;
        }
        products.remove(product.getId());
        return "redirect:/api/products";
    }

    private boolean handleNonExistProduct(Product product, HttpServletResponse httpServletResponse) {
        if (!products.containsKey(product.getId())) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            try {
                httpServletResponse.sendRedirect("/api/products");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private boolean handleAlreadyExist(boolean containsProductKey, HttpServletResponse response) {
        if (containsProductKey) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.sendRedirect("/api/products");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private boolean handleBadAttribute(HttpServletResponse response, Product newProduct) {
        if (newProduct.getId() == null|| newProduct.getId() < 0 || newProduct.getName().isEmpty()
            || newProduct.getPrice() < 0
            || newProduct.getImageUrl().isEmpty()) {
            // BAD_REQUEST임을 알리는 동시에 다시 리다이렉트 시킬 수 있다.
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            try {
                response.sendRedirect("/api/products");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }
}


