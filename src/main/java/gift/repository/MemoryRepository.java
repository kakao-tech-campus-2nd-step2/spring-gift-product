package gift.repository;

import gift.dto.ProductDTO;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryRepository {

    private final Map<Long, ProductDTO> products = new HashMap<>();
    private long sequence = 1;

    public Collection<ProductDTO> getProducts() {
        return products.values();
    }

    public ProductDTO getProduct(Long sequence) {
        return products.get(sequence);
    }

    public void addProduct(ProductDTO product) {
        products.put(sequence++, product);
    }

    public void updateProduct(long sequence, ProductDTO product) {
        products.replace(sequence, product);
    }

    public void deleteProduct(long sequence) {
        products.remove(sequence);
    }
}
