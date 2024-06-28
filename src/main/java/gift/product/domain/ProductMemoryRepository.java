package gift.product.domain;

import gift.product.application.command.ProductCreateCommand;
import gift.product.application.command.ProductUpdateCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductMemoryRepository implements ProductRepository {
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
        Long nextId = products.isEmpty() ? 1L : products.getLast().getId() + 1L;

        products.add(
                new Product(
                        nextId,
                        product.name(),
                        product.price(),
                        product.imageUrl()
                )
        );
    }

    public void deleteProduct(Long productId) {
        products.removeIf(product -> product.getId().equals(productId));
    }

    @Override
    public void updateProduct(ProductUpdateCommand command) {
        products.stream()
                .filter(product -> product.getId().equals(command.productId()))
                .findFirst()
                .ifPresent(product -> {
                    product.update(
                            command.name(),
                            command.price(),
                            command.imageUrl()
                    );
                });
    }
}
