package gift.domain;

import java.util.HashMap;
import java.util.Map;

public class Products {

    private final Map<Long, Product> products;

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        products.put(product.getId(), product);
    }

    public void delete(Long id) {
        products.remove(id);
    }

    public void edit(Long id, Product product) {
        products.remove(id);
        products.put(product.getId(), product);
    }

    public Products() {
        this.products = new HashMap<>();
    }
}

