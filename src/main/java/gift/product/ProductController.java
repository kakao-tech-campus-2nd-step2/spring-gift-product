package gift.product;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final Map<Long, Product> products = new HashMap<>();

    @GetMapping("/products")
    public List<ProductResDto> getProducts() {
        Collection<Product> productList = products.values();

        return productList.stream()
                .map(product -> new ProductResDto(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getImageUrl()
                )).toList();
    }

    @GetMapping("/products/{productId}")
    public ProductResDto getProduct(@PathVariable Long productId) {
        Product product = products.get(productId);

        return new ProductResDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }

    @PostMapping("/products")
    public ProductResDto addProduct(@RequestBody ProductReqDto productReqDto) {
        Product newProduct = new Product(
                productReqDto.name(),
                productReqDto.price(),
                productReqDto.imageUrl()
        );

        products.put(newProduct.getId(), newProduct);

        return new ProductResDto(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getPrice(),
                newProduct.getImageUrl()
        );
    }

    @PutMapping("/products/{productId}")
    public ProductResDto updateProduct(@PathVariable Long productId, @RequestBody ProductReqDto productReqDto) {
        Product product = products.get(productId);

        product.setName(productReqDto.name());
        product.setPrice(productReqDto.price());
        product.setImageUrl(productReqDto.imageUrl());

        return new ProductResDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl()
        );
    }
}
