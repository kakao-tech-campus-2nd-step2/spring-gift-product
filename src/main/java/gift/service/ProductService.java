package gift.service;

import gift.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    public ProductService() {
        // 초기 데이터 추가
        Product product = new Product.Builder()
                .id(8146027L)
                .name("아이스 카페 아메리카노 T")
                .price(4500)
                .imageUrl("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg")
                .build();
        products.put(product.getId(), product);
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    public void updateProduct(Long id, Product updatedProduct) {
        if (products.containsKey(id)) {
            products.put(id, new Product.Builder()
                    .id(id)
                    .name(updatedProduct.getName())
                    .price(updatedProduct.getPrice())
                    .imageUrl(updatedProduct.getImageUrl())
                    .build());
        }
    }

    public void deleteProduct(Long id) {
        products.remove(id);
    }
}
