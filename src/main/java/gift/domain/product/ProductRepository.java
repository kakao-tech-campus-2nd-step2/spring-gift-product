package gift.domain.product;

import java.util.HashMap;
import java.util.Map;

public class ProductRepository {
    private Map<Long,Product> storage = new HashMap<>();

    private static long sequence = 0L;

    public static final ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    private ProductRepository(){};

    public void save(Product product){
        product.setId(++sequence);
        storage.put(product.getId(),product);
    }
}
