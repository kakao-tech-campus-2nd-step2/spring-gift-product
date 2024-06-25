package gift.product;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private final Map<Long, ProductVo> products = new HashMap<>();

    public void addProduct(ProductVo product) {
        products.put(product.getId(), product);
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
