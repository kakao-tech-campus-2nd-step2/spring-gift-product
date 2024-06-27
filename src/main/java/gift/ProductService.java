package gift;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
}
