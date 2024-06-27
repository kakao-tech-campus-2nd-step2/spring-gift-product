package gift;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private long currentId = 1;

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public void addProduct(Product product) {
        product.setId(currentId++);
        products.put(product.getId(), product);

    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }

    public void modifyProduct(Long id, Product product) {
        product.setId(id);
        products.put(id, product);
    }


}
