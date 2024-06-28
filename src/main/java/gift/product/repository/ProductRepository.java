package gift.product.repository;

import gift.product.Product;
import java.util.List;

public interface ProductRepository {
    List<Product> findAllProducts();
    Product findProductById(Long id);
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    Long deleteProduct(Long id);
    boolean isExist(Long id);
}
