package gift.controller;

import gift.domain.Product;
import gift.service.ProductService;
import gift.util.ImageStorageUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestPart MultipartFile imageFile,
                                              @RequestParam Long id,
                                              @RequestParam String name,
                                              @RequestParam Long price,
                                              @RequestParam String description) {
        try {
            String imagePath = ImageStorageUtil.saveImage(imageFile, id);

            String base64EncodedImagePath = ImageStorageUtil.encodeImagePathToBase64(imagePath);

            String imageUrl = base64EncodedImagePath;
            Product product = new Product(id, name, price, description, imageUrl);

            productService.addProduct(product);

            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestPart MultipartFile imageFile,
                                                 @RequestParam String name,
                                                 @RequestParam Long price,
                                                 @RequestParam String description) {
        try {
            Product product = productService.getProductById(id);

            if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
                ImageStorageUtil.deleteImage(product.getImageUrl());
            }

            String imagePath = ImageStorageUtil.saveImage(imageFile, id);

            String base64EncodedImagePath = ImageStorageUtil.encodeImagePathToBase64(imagePath);

            String imageUrl = base64EncodedImagePath;
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImageUrl(imageUrl);

            productService.updateProduct(product);

            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}