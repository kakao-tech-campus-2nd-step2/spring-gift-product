package gift.service;

import gift.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private Long nextId = 1L;

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void saveProduct(Product product) {
        if (product.getId() == 0) {
            product.setId(nextId++);
        }
        products.put(product.getId(), product);
    }

    public Product getProductById(long id) {
        return products.get(id);
    }

    public void deleteProductById(long id) {
        products.remove(id);
    }
}