package gift.product.domain;

import gift.product.application.command.ProductCreateCommand;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Long productId) {
        return products.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst();
    }

    public void addProduct(ProductCreateCommand product) {
        products.add(
            new Product(
            products.size() + 1L,
                product.name(),
                product.price(),
                product.imageUrl()
            )
        );
    }

    public void deleteProduct(Long productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }
}
