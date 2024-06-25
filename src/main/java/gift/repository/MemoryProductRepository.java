package gift.repository;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryProductRepository implements ProductRepository {

    private Map<Long, Product> db = new HashMap<>();
    private static Long id = 0L;

    @Override
    public Product save(ProductForm form) {
        Product product = new Product();
        product.setId(++id);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImageUrl());

        db.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Product result = db.remove(id);
        return result != null;
    }

    @Override
    public Product edit(Long id, ProductForm form) {
        Product result = findById(id);
        if (result == null) {
            return null;
        }
        result.setName(form.getName());
        result.setPrice(form.getPrice());
        result.setImageUrl(form.getImageUrl());
        return result;
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
