package gift.db;


import gift.dto.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@Qualifier("MEMORY DATABASE")
public class ProductMemoryDB implements ProductDB {
    private final Map<Long, Product> products = new HashMap<>();

    private ProductMemoryDB() {
        //초기 상품
        Product defaultProduct1 = new Product(1L, "Americano", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        products.put(1L, defaultProduct1);
        Product defaultProduct2 = new Product(2L, "Latte", 5500, "https://cdn.pixabay.com/photo/2023/07/08/13/17/coffee-8114518_1280.png");
        products.put(2L, defaultProduct2);
        Product defaultProduct3 = new Product(3L, "Sandwich", 7700, "https://cdn.pixabay.com/photo/2023/08/12/02/58/sandwich-8184642_1280.png");
        products.put(3L, defaultProduct3);
        Product defaultProduct4 = new Product(4L, "Cup Cake", 10000, "https://cdn.pixabay.com/photo/2023/05/31/14/41/ai-generated-8031574_1280.png");
        products.put(4L, defaultProduct4);
    }

    // Example method to interact with the products map
    @Override
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product getProduct(Long id) {
        return products.get(id);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(entry.getValue());
        }
        return productList;
    }
    @Override
    public void removeProduct(Long id) {
        products.remove(id);
    }

    @Override
    public void removeProducts(List<Long> productIds) {
        for (Long id : productIds) {
            products.remove(id);
        }
    }
    @Override
    public void editProduct(Product product) {
        products.put(product.getId(), product);
    }
    public boolean hasProductId(Long id) {
        if (products.containsKey(id)) {
            return true;
        }
        return false;
    }
}
