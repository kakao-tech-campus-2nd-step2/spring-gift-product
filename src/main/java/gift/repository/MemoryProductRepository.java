package gift.repository;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryProductRepository implements ProductRepository {

    private Map<Long, Product> db = new HashMap<>();
    private static Long id = 0L;

    @Override
    public Product save(Product product) {
        product.setId(++id); // ID 증가 후 설정
        db.put(product.getId(), product); // Map에 제품 저장
        return product;
    }

    @Override
    public boolean delete(Long id) {
        Product result = db.remove(id); // ID에 해당하는 제품 삭제
        return result != null; // 삭제 성공 여부 반환
    }

    @Override
    public Product edit(Long id, ProductForm form) {
        Product product = findById(id); // ID에 해당하는 제품 조회
        if (product == null) {
            return null; // 제품이 없으면 null 반환
        }
        // 제품 정보 수정
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImageUrl());
        return product;
    }

    @Override
    public Product findById(Long id) {
        return db.get(id); // ID에 해당하는 제품 조회
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(db.values()); // 모든 제품 목록 반환
    }

    // 메모리 DB를 초기화하는 메서드
    public void clearDB() {
        db.clear();
    }
}
