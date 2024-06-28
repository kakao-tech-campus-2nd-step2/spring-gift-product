package gift.repository;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.List;

public interface ProductRepository {

    /**
     * 제품 정보를 저장합니다.
     *
     * @param form 저장할 제품의 정보를 담고 있는 ProductForm 객체
     * @return 저장된 제품 객체
     */
    Product save(ProductForm form);

    /**
     * 주어진 ID에 해당하는 제품을 삭제합니다.
     *
     * @param id 삭제할 제품의 ID
     * @return 삭제 성공 여부를 나타내는 boolean 값
     */
    boolean delete(Long id);

    /**
     * 주어진 ID에 해당하는 제품의 정보를 수정합니다.
     *
     * @param id   수정할 제품의 ID
     * @param form 수정할 제품의 정보를 담고 있는 ProductForm 객체
     * @return 수정된 제품 객체
     */
    Product edit(Long id, ProductForm form);

    /**
     * 주어진 ID에 해당하는 제품을 조회합니다.
     *
     * @param id 조회할 제품의 ID
     * @return 조회된 제품 객체
     */
    Product findById(Long id);

    /**
     * 데이터베이스에 저장된 모든 제품 목록을 조회합니다.
     *
     * @return 모든 제품의 목록을 담고 있는 List 객체
     */
    List<Product> findAll();
}
