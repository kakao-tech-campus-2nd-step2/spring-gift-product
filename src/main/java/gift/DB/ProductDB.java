package gift.DB;

import gift.DTO.ProductDTO;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDB {

    private static final Map<Long, ProductDTO> products = new ConcurrentHashMap<>();

    public List<ProductDTO> getList() {
        return products.values().stream().toList();
    }

    public ProductDTO getProduct(Long id) {
        return products.get(id);
    }

    public int getLastId() {
        return products.size();
    }

    public void setProduct(long id, ProductDTO product) {
        products.put(id, product);
    }

    public void updateProduct(long id, ProductDTO product) {
        products.replace(id, product);
    }

    public void removeProduct(long id) {
        products.remove(id);
    }

    public Boolean validateId(Long id) {
        return products.containsKey(id);
    }

}
