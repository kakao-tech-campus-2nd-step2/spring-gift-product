package gift.controller;

import gift.controller.dto.ProductCreateRequestDto;
import gift.controller.dto.ProductResponseDto;
import gift.controller.dto.ProductUpdateRequestDto;
import gift.domain.Product;
import gift.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final ProductRepository products;

    @Autowired
    public ProductController(ProductRepository products) {
        this.products = products;
    }

    @GetMapping("/products")
    public List<ProductResponseDto> getAllProducts() {
        return products
            .findAll()
            .stream()
            .map(ProductResponseDto::from)
            .toList();
    }

    @GetMapping("/products/{id}")
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return ProductResponseDto.from(products.get(id));
    }

    @PostMapping("/products")
    public void addProduct(
        @RequestBody ProductCreateRequestDto productCreateRequest
    ) {
        Product product = productOf(productCreateRequest);
        products.save(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(
        @PathVariable Long id,
        @RequestBody ProductUpdateRequestDto productUpdateRequest
    ) {
        Product originalProduct = products.get(id);
        if (originalProduct == null) {
            throw new RuntimeException("Product not found");
        }
        Product updatedProduct = applyUpdate(originalProduct, productUpdateRequest);
        products.save(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }

    private Product productOf(ProductCreateRequestDto productCreateRequest) {
        return new Product(
            0L,
            productCreateRequest.name(),
            productCreateRequest.price(),
            productCreateRequest.imageUrl()
        );
    }

    private Product applyUpdate(Product originalProduct,
        ProductUpdateRequestDto productUpdateRequest) {
        String name = originalProduct.name();
        if (productUpdateRequest.name() != null) {
            name = productUpdateRequest.name();
        }

        Integer price = originalProduct.price();
        if (productUpdateRequest.price() != null) {
            price = productUpdateRequest.price();
        }

        String imageUrl = originalProduct.imageUrl();
        if (productUpdateRequest.imageUrl() != null) {
            imageUrl = productUpdateRequest.imageUrl();
        }
        return new Product(originalProduct.id(), name, price, imageUrl);
    }
}
