package gift.service;

import gift.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final Map<Long, Product> products = new HashMap<>();
    private Long idCounter = 1L;

    //상품 추가 기능
    public Product addProduct(Product product) {
        product.setId(idCounter);
        products.put(idCounter++, product);
        return product;
    }

    //상품 전체 조회 기능
    public List<Product> selectAllProduct() {
        return new ArrayList<>(products.values());
    }

    //상품 단일 조회 기능
    public Product selectProductById(Long id) {
        return products.get(id);
    }

    //상품 삭제 기능
    public void deleteProduct(Long id) {
        Product existingProduct = products.get(id);
        if (existingProduct != null) {
            products.remove(id);
        } else {
            throw new IllegalArgumentException("No Exists Product By Id");
        }
    }

    //상품 수정 기능
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = products.get(id);
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            existingProduct.setImageUrl(product.getImageUrl());
            products.put(id, existingProduct);
            return existingProduct;
        } else {
            throw new IllegalArgumentException("No Exists Product By Id");
        }
    }
}
