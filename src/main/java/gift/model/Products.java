package gift.model;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

public class Products implements ProductRepository{
    private final TreeMap<Long, Product> products;

    public Products() {
        this.products = new TreeMap<>();
    }

    @Override
    public void save(Product entity) {
        if(entity.getId() == null){
            Long newId = null;
            while(newId == null || products.containsKey(newId)){
                newId = UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE;
            }
            entity.setId(newId);
        }
        products.put(entity.getId(), entity);
    }

    @Override
    public Product find(Long id) {
        return products.getOrDefault(id, null);
    }

    @Override
    public void delete(Product entity) {
        products.remove(entity.getId());
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }
}

