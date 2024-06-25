package gift.domain;

import java.util.HashMap;
import java.util.Map;

public class Products {

    private final Map<Long, Product> products;

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        products.put(product.getId(), product);
    }

    public boolean delete(Long id) {
        //간단한 유효성 검증. 삭제하려는 id값이 존재하지 않으면 false, 삭제를 올바르게 마쳤다면 true를 리턴한다.
        return products.remove(id) != null;
    }

    public boolean edit(Long id, Product product) {

        //간단한 유효성 검증. id값이 존재하지 않으면 false를 리턴한다
        if (products.remove(id) != null) {
            products.put(product.getId(), product);
            return true;
        }

        return false;
    }

    public Products() {
        this.products = new HashMap<>();
    }
}

