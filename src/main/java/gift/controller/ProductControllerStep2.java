package gift.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import gift.entity.Product;
import gift.dto.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class ProductControllerStep2 {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("pinkbeen/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", products);
        return "index";
    }

    @PostMapping("pinkbeen/products")
    public String addProduct(@ModelAttribute ProductDTO productDTO) {
        Product newProduct = new Product(productDTO.id(), productDTO.name(), productDTO.price(), productDTO.imageUrl());
        products.put(newProduct.getId(), newProduct);

        return "redirect:/pinkbeen/products";
    }

    @PostMapping("pinkbeen/products/{id}")
    public ResponseEntity<ProductDTO> modifyProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        Product updatedProduct = products.get(id);

        if (!Objects.equals(id, productDTO.id())) {
            products.remove(id);
            updatedProduct.setId(productDTO.id());
            products.put(productDTO.id(), updatedProduct);
        }

        updatedProduct.setName(productDTO.name());
        updatedProduct.setPrice(productDTO.price());
        updatedProduct.setImageUrl(productDTO.imageUrl());

        ProductDTO updatedProductDTO = new ProductDTO(
                updatedProduct.getId(),
                updatedProduct.getName(),
                updatedProduct.getPrice(),
                updatedProduct.getImageUrl()
        );

        return ResponseEntity.ok(updatedProductDTO);
    }

    @DeleteMapping("pinkbeen/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        products.remove(id);
        return "redirect:/pinkbeen/products";
    }
}