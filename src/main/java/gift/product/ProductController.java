package gift.product;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private final Map<Long, ProductVo> products = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public void addProduct(ProductVo product) {
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
