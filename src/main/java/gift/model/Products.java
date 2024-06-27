package gift.model;

import gift.exception.ValidationException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

// Product 객체를 저장하는 일급 컬렉션
public class Products {

    // id를 통한 빠른 탐색을 위해 HashMap을 사용.
    private Map<Long, Product> products;

    // 생성자
    public Products() {
        products = new HashMap<>();
    }

    // map에 제품을 넣는 메서드
    public Product put(Product product) {
        long id = product.getId();
        verifyProductAlreadyExist(id);
        products.put(id, product);

        // 반환할 필요는 없지만 잘 들어 갔는지 확인하기 위함.
        return products.get(id);
    }

    // id에 맞는 상품 조회
    public Product getProduct(long id) {
        // 도메인 규칙 검사
        verifyProductExist(id);
        // 멀쩡하면 불러오기
        Product product = products.get(id);

        return product;
    }

    // 전체 상품 조회
    public Collection<Product> getProducts() {
        // products의 value만 가져오기
        Collection<Product> productsCollection = products.values();

        return productsCollection;
    }

    // id에 맞는 데이터를 삭제
    public void deleteProduct(long id) {
        // 우선 해당하는 id가 있는지부터 검사
        verifyProductExist(id);

        // 있다면 삭제하기
        products.remove(id);
    }

    // 모든 데이터를 삭제
    public void deleteAllProducts() {
        products.clear();
    }

    // 상품 수정하기 (patch)
    public Product updateProduct(long targetId, Product product) {
        // 이미 존재하는 id로 수정하면 안됨(자신 포함)
        long id = product.getId();
        verifyProductAlreadyExist(id);

        // products에서의 검증이 끝났으면 product에서 검증 후 변경
        Product targetProduct = products.get(targetId);
        targetProduct.updateAll(product);

        // map 구조 때문에 들어간 부분. id가 바뀐 경우 map도 바꿔줘야 한다.
        // map의 old key를 삭제하고, new key로 업데이트하여 삽입
        deleteProduct(targetId);
        put(targetProduct);

        // 잘 들어 갔는지 확인하기 위함
        return targetProduct;
    }

    // 도메인 규칙: 삽입 시에는 id가 중복되지 않아야 한다.
    private void verifyProductAlreadyExist(long id) {
        if (products.containsKey(id)) {
            throw new ValidationException("id가 중복됩니다.");
        }
    }

    // 도메인 규칙: 조회 시에는 id가 존재해야 한다.
    private void verifyProductExist(long id) {
        if (!products.containsKey(id)) {
            throw new NoSuchElementException("해당 id를 가진 제품이 존재하지 않습니다.");
        }
    }

}
