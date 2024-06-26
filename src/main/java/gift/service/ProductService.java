pacakge gift.service;

import gift.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products=new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    public void addProudct(Product product) {
        products.put(product.getId(), product);
    }
    public void updateProduct(Long id, Product updatedProduct) {
        if (products.containsKey(id)) {
            products.put(id, new Product.Builder()
                    .id(id)
                    .name(updatedProduct.getName())
                    .price(updatedProduct.getPrice())
                    .imageUrl(updatedProduct.getImageUrl())
                    .build());
        }
    }
    public void deleteProduct(Long id) {
        products.remove(id);
    }
}