package gift;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductMemoryDB {
    private static final ProductMemoryDB INSTANCE = new ProductMemoryDB();

    private final Map<Long, Product> products = new HashMap<>();

    // Private constructor to prevent instantiation
    private ProductMemoryDB() {
        //초기 상품
        Product testProduct = new Product(1L, "Americano", 4500, "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        products.put(1L, testProduct);
    }

    public static ProductMemoryDB getInstance() {
        return INSTANCE;
    }

    // Example method to interact with the products map
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public Product getProduct(Long id) {
        return products.get(id);
    }
    public List<Product> getProducts(){
        List<Product> productList = new ArrayList<>();
        for (Map.Entry<Long, Product> entry : products.entrySet()) {
            productList.add(entry.getValue());
        }
        return productList;


    }

    public boolean hasProductId(Long id) {
        if (products.containsKey(id)) {
            return true;
        }
        return false;
    }

    public void removeProduct(Long id) {
        products.remove(id);
    }

}
