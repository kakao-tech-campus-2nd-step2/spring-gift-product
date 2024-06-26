package gift.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import gift.model.Product;

@Service
public class ProductService {
    
    private Map<Long, Product> products = new HashMap<>();
    
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    public Product getProductById(long id) {
        return products.get(id);
    }
    
    public Product addProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }
    
    public Product updateProduct(long id, Product product) {
        if(!products.containsKey(id)) {
            return null;
        }
        product.setId(id);
        products.put(id, product);
        return product;
    }
    
    public boolean deleteProduct(long id) {
        if(!products.containsKey(id)) {
            return false;
        }
        products.remove(id);
        return true;
    }
}
