package gift;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Long, ProductModel> products = new HashMap<>(); // 상품 데이터를 저장하는 자료구조
    private long currentId = 0; // 추가될 id를 유지하기 위한 변수

    // 현재 저장된 모든 상품들을 반환
    public Collection<ProductModel> getAllProducts() {
        return products.values();
    }

    // 주어진 id에 해당하는 상품 객체를 반환
    public ProductModel getProductById(long id) {
        return products.get(id);
    }

    // 상품 추가
    public ProductModel addProduct(ProductModel product) {
        product.id = ++currentId;
        products.put(product.id, product);
        return product;
    }

    // 상품 업데이트
    public ProductModel updateProduct(long id, ProductModel updatedProduct) {
        ProductModel product = products.get(id);
        if (product != null) {
            product.category = updatedProduct.category; // 카테고리 업데이트
            product.name = updatedProduct.name; // 이름 업데이트
            product.price = updatedProduct.price; // 가격 업데이트
            products.put(id, product);
            return product;
        }
        return null;
    }

    // 상품 삭제
    public boolean deleteProduct(long id) {
        return products.remove(id) != null;
    }
}