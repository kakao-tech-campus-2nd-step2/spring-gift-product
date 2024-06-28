package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    public List<Product> getAllProducts(){
        return new ArrayList<>(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public void addProduct(Product newProduct){
        products.put(newProduct.id(), newProduct);
    }

    public void updateProduct(long id, Product updatedProduct){
        products.put(id, updatedProduct);
    }

    public void deleteProduct(long id){
        products.remove(id);
    }


}
