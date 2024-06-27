// ProductDAO.class
package gift.repository;

import gift.model.Product;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new HashMap<>();

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public Product getProduct(Long id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void updateProduct(Long id, Product product) {
        products.put(id, product);
    }

    public boolean containsProduct(Long id) {
        return products.containsKey(id);
    }

    public void removeProduct(Long id) {
        products.remove(id);
    }
}
