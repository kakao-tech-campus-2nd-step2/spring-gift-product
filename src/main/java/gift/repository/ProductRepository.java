package gift.repository;

import gift.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private List<Product> products = new ArrayList<>();
    private AtomicLong counter = new AtomicLong(1);

    public List<Product> findAll() {
        return products;
    }

    public void save(Product product) {
        product.setId(counter.getAndIncrement());
        products.add(product);
    }

    public Optional<Product> findById(long id) {
        return products.stream().filter(product -> product.getId() == id).findFirst();
    }

    public void update(Product product) {
        findById(product.getId()).ifPresent(existingProduct -> {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());
        });
    }

    public void deleteById(long id) {
        products.removeIf(product -> product.getId() == id);
    }
}
