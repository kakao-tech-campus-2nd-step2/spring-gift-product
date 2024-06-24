package gift.repository;

import gift.domain.Product;

import java.util.*;

public class ProductMemoryRepository implements ProductRepository{

    private static Map<Long, Product> products = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++sequence);
        products.put(sequence, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Long delete(Long id) {
        findById(id).orElseThrow(IllegalAccessError::new);
        products.remove(id);
        return id;
    }
}
