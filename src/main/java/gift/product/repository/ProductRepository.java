package gift.product.repository;

import gift.product.model.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();

    private Long accId = 1L;

    public void save(Product product) {
        Long productId = accId++;
        product.setId(productId);
        products.put(productId, product);
    }
}
