package gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ProductController {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public ProductController() {
        Product product1 = new Product(idGenerator.getAndIncrement(), "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        products.put(product1.getId(), product1);
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }

    public Product addProduct(Product product) {
        long id = idGenerator.getAndIncrement();
        product.setId(id);
        products.put(id, product);
        return product;
    }
    public Product updateProduct(long id, Product newProductData) {
        Product existingProduct = products.get(id);
        if (existingProduct == null) {
            return null;
        }
        existingProduct.setName(newProductData.getName());
        existingProduct.setPrice(newProductData.getPrice());
        existingProduct.setImageUrl(newProductData.getImageUrl());
        return existingProduct;
    }
    public Product deleteProduct(long id) {
        return products.remove(id);
    }
}
