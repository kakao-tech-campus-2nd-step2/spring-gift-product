package gift;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long nextId = 1;

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(long id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
        long id = nextId++;
        product.setId(id);
        products.put(id, product);
    }

    public void updateProduct(long id, Product product) {
        products.put(id, product);
    }

    public void deleteProduct(long id) {
        products.remove(id);
    }
}
