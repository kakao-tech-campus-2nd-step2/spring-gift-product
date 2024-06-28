package gift.repository;

import gift.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryProductRepository implements ProductRepository {

    private Map<Long, Product> db = new HashMap<>();
    private static Long id = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++id);
        db.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Product result = db.remove(id);
        return result != null;
    }

    @Override
    public boolean edit(Long id, Product product) {
        Product result = findById(id);
        if (result == null) {
            return false;
        }
        result.setProduct(product);
        return true;
    }

    @Override
    public Product findById(Long id) {
        return db.get(id);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(db.values());
    }

    public void clearDB() {
        db.clear();
    }
}