package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        // product가 존재하지 않으면 null 반환
        if(products.isEmpty()) {
            return null;
        }
        return new ArrayList<>();
    }

}
