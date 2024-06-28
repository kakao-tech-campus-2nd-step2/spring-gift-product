package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductRepository products;

    @Autowired
    public ProductController(ProductRepository products) {
        this.products = products;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return products
                .findAll()
                .stream()
                .map(ProductResponse::from)
                .toList();
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return ProductResponse.from(products.get(id));
    }

    @PostMapping("/products")
    public void addProduct(
            @RequestBody ProductCreateRequest productCreateRequest
    ) {
        Product product = productOf(productCreateRequest);
        products.save(product);
    }

    @PutMapping("/products/{id}")
    public void updateProduct(
            @PathVariable Long id,
            @RequestBody ProductUpdateRequest productUpdateRequest
    ) {
        Product originalProduct = products.get(id);
        if (originalProduct == null) {
            throw new ProductNotFoundException();
        }
        Product updatedProduct = applyUpdate(originalProduct, productUpdateRequest);
        products.save(updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }

    private Product productOf(ProductCreateRequest productCreateRequest) {
        return new Product(
            0L,
                productCreateRequest.name(),
                productCreateRequest.price(),
                productCreateRequest.imageUrl()
        );
    }

    private Product applyUpdate(Product originalProduct, ProductUpdateRequest productUpdateRequest) {
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
