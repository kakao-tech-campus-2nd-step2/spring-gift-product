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

    private Product productOf(ProductCreateRequest productCreateRequest) {
        return new Product(
                products.size() + 1L,
                productCreateRequest.name(),
                productCreateRequest.price(),
                productCreateRequest.imageUrl()
        );
    }
}
