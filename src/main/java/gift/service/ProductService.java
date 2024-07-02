package gift.service;

import gift.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void createProduct(Product product);
    void updateProduct(Long id, Product product);
    void patchProduct(Long id, Map<String, Object> updates);
    List<Product> patchProducts(List<Map<String, Object>> updatesList);
    void deleteProduct(Long id);
    List<Product> getProducts(int page, int size);
}
