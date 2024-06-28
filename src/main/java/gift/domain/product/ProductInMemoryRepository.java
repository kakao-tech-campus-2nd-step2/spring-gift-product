package gift.domain.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository implements ProductRepository {
    private final Map<Long,Product> storage = new HashMap<>();

    private static long sequence = 0L;

    public static final ProductInMemoryRepository instance = new ProductInMemoryRepository();

    public static ProductInMemoryRepository getInstance() {
        return instance;
    }

    private ProductInMemoryRepository(){}

    @Override
    public void save(Product product){
        product.setId(++sequence);
        storage.put(product.getId(),product);
    }

    @Override
    public ArrayList<Product> findAll(){
        return new ArrayList<>(storage.values());
    }

    @Override
    public Product findById(Long id){
        return storage.get(id);
    }

    @Override
    public void deleteById(Long id){
        storage.remove(id);
    }
}
