package gift.repository;

import gift.model.Product;
import gift.model.ProductDto;
import java.util.*;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products;
    private Long nextId;

    public ProductRepository() {
        this.products = new HashMap<>();
        this.nextId = 1L;
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    public Optional<Product> findByContents(ProductDto dto) {
        return products.values().stream()
            .filter(product -> product.name().equals(dto.name()) &&
                product.price().equals(dto.price()) &&
                product.imageUrl().equals(dto.imageUrl()))
            .findFirst();
    }

    public Product update(Long id, ProductDto product) {
        return products.put(id, new Product(id, product.name(), product.price(), product.imageUrl()));
    }

    public Product save(ProductDto product) {
        Product ret = new Product(nextId, product.name(), product.price(), product.imageUrl());
        products.put(nextId, ret);
        nextId++;
        return ret;
    }

    public void deleteById(Long id) {
        products.remove(id);
    }
}
