package gift;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts() {
        return products
                .values()
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
        products.put(product.id(), product);
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
        products.put(id, updatedProduct);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        products.remove(id);
    }

    private Product productOf(ProductCreateRequest productCreateRequest) {
        return new Product(
                products.size() + 1L,
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
