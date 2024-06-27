package gift.domain.repository;

import gift.domain.model.Product;
import gift.domain.model.ProductRequestDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final static Map<Long, Product> products = new HashMap<>();

    public boolean isExistProductId(Long id) {
        return products.containsKey(id);
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public boolean isEmptyProductList() {
        return products.isEmpty();
    }

    public List<Product> getAllProduct() {
        return new ArrayList<>(products.values());
    }

    public void addProduct(ProductRequestDto productRequestDto) {
        products.put(productRequestDto.getId(), productRequestDto.toProduct());
    }

    public void updateProduct(ProductRequestDto productRequestDto) {
        products.replace(productRequestDto.getId(), productRequestDto.toProduct());
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}
