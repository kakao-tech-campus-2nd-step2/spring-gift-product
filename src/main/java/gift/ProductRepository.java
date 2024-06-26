package gift;

import gift.exception.ProductNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepository {
    private final Map<Long,Product> products = new HashMap<>();
    private Long nextId = 1L;

    public void addProduct(Product product) {
        products.put(nextId++,product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) throws ProductNotFoundException{
        validateProduct(id);
        return products.get(id);
    }

    private boolean validateProduct(Long id) throws ProductNotFoundException{
        Product product = products.get(id);
        if (product == null){
            throw new ProductNotFoundException("product is not found");
        }
        return true;
    }
}
