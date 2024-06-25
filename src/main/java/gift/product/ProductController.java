package gift.product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, ProductVo> products = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @PostMapping("/product")
    public void addProduct(@RequestParam("name") String name, @RequestParam("price") int price, @RequestParam("imageUrl") String imageUrl) {
        ProductVo product = new ProductVo();

        product.setName(name);
        product.setPrice(price);
        product.setImageUrl(imageUrl);

        products.put(idCounter.incrementAndGet(), product);
    }

    public void modifyProduct(ProductVo product) {
        products.put(product.getId(), product);
    }

    public ProductVo selectProduct(Long id) {
        return products.get(id);
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}
