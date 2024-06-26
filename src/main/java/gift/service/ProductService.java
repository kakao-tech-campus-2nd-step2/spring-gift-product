package gift.service;

import gift.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private final Map<Long, Product> products = new HashMap<>();

    // Read(전체 상품)
    public Collection<Product> productGET() {
        return products.values();
    }

    // Read(단일 상품)
    public Product productGET(Long id) {
        return products.get(id);
    }

    // Create(생성)
    public String productPost(Product product) {
        if (!products.containsKey(product.getId())) {
            products.put(product.getId(), product);
            return "상품 생성";
        } else {
            return "상품이 이미 존재합니다";
        }
    }

    // Update(수정)
    public String productPut(Long id, Product product) {
        if (products.containsKey(id)) {
            products.put(id, product);
            return "상품 수정";
        } else {
            return "존재하지 않는 상품입니다.";
        }
    }

    // Delete(삭제)
    public String productDelete(Long id) {
        if (products.containsKey(id)) {
            products.remove(id);
            return "상품 삭제";
        } else {
            return "삭제할 상품이 존재하지 않습니다.";
        }
    }
}
