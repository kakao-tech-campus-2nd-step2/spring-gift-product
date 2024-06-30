package gift.repository;

import gift.model.ProductDAO;
import gift.model.ProductDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryProductRepository implements ProductRepository {

    private Map<Long, ProductDAO> db = new HashMap<>();
    private static Long id = 0L;

    @Override
    public ProductDAO save(ProductDTO form) {
        ProductDAO product = new ProductDAO();
        product.setId(++id);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImageUrl());

        db.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean delete(Long id) {
        ProductDAO result = db.remove(id);
        return result != null;
    }

    @Override
    public ProductDAO edit(Long id, ProductDTO form) {
        ProductDAO result = findById(id);
        if (result == null) {
            return null;
        }
        result.setName(form.getName());
        result.setPrice(form.getPrice());
        result.setImageUrl(form.getImageUrl());
        return result;
    }

    @Override
    public ProductDAO findById(Long id) {
        return db.get(id);
    }

    @Override
    public List<ProductDAO> findAll() {
        return new ArrayList<>(db.values());
    }

    public void clearDB() {
        db.clear();
    }
}
