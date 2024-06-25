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
        return new ArrayList<>(products.values());
    }

    public Product getProductById(long id) {
        if(products.containsKey(id)) {
            return products.get(id);
        }
        throw new IllegalArgumentException("[ERROR] ID가 " + id + "인 상품을 찾을 수 없습니다.");
    }

    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    public void updateProduct(Product product) {
        if(!products.containsKey(product.id())) {
            throw new IllegalArgumentException(
                "[ERROR] ID가 " + product.id() + "인 상품을 찾을 수 없어 수정할 수 없습니다."
            );
        }

        if(product.id() == null) {
            throw new IllegalArgumentException("[ERROR] ID는 비워둘 수 없습니다.");
        }

        products.put(product.id(), product);
    }

    public void deleteProduct(Long id) {
        if(!products.containsKey(id)) {
            throw new IllegalArgumentException(
                "[ERROR] ID가 " + id + "인 상품을 찾을 수 없어 삭제할 수 없습니다."
            );
        }

        products.remove(id);
    }
}
