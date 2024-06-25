package gift.product;

import java.util.HashMap;
import java.util.Map;

public class ProductController {

    private final Map<Long, ProductVo> products = new HashMap<>();

    public void addProduct(ProductVo product) {
        products.put((long) (products.size()+1), product);
    }

    public void modifyProduct(Long id, ProductVo product) {
        products.put(id, product);
    }
}
