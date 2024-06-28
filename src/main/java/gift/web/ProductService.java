package gift.web;

import gift.web.dto.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong incrementCounter = new AtomicLong(1); // ID를 관리할 변수

    public List<Product> getProducts() {
        return List.copyOf(products.values());
    }

    public Product getProductById(Long id) {
        return products.get(id);
    }

    public Product createProduct(Product product) {
        Long id = incrementCounter.getAndIncrement(); // 1씩 증가하는 id
        Product newProduct = new Product(id, product.name(), product.price(), product.imageUrl());
        products.put(id, newProduct);

        return newProduct;
    }

    public Product updateProduct(Long id, Product product) {
        products.put(id, product);
        return product;
    }


}
