package gift.repository;

import static org.assertj.core.api.Assertions.assertThat;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MemoryProductRepositoryTest {

    MemoryProductRepository repository = new MemoryProductRepository();

    @AfterEach
    public void afterEach() {
        repository.clearDB(); // 각 테스트 종료 후 메모리 데이터 초기화
    }

    @Test
    public void save() {
        // 제품 저장 테스트
        ProductForm productForm = new ProductForm("abc", 123, "www.test.com");
        Product savedProduct = repository.save(productForm);

        Product result = repository.findById(savedProduct.getId());

        assertThat(result).isEqualTo(savedProduct); // 저장된 제품과 조회된 제품 비교
    }

    @Test
    @DisplayName("존재하는 ID 삭제")
    public void delete_exist() {
        // 존재하는 제품 ID로 삭제 테스트
        ProductForm productForm = new ProductForm("abc", 123, "www.test.com");
        Product savedProduct = repository.save(productForm);

        boolean result = repository.delete(savedProduct.getId());

        assertThat(result).isEqualTo(true); // 삭제 결과 검증
    }

    @Test
    @DisplayName("존재하지 않는 ID 삭제")
    public void delete_nonexistent() {
        // 존재하지 않는 제품 ID로 삭제 시도 테스트
        ProductForm productForm = new ProductForm("abc", 123, "www.test.com");
        repository.save(productForm);

        boolean result = repository.delete(0L);

        assertThat(result).isEqualTo(false); // 삭제 결과 검증
    }

    @Test
    public void edit() {
        // 제품 정보 수정 테스트
        ProductForm productForm = new ProductForm("abc", 123, "www.test.com");
        Product savedProduct = repository.save(productForm);

        Product editedProduct = repository.edit(savedProduct.getId(),
            new ProductForm("def", 123, "www.test.com"));

        assertThat(editedProduct).isNotNull(); // 수정된 제품이 null이 아님을 검증
        assertThat(editedProduct.getName()).isEqualTo("def"); // 수정된 제품의 이름 검증
    }

    @Test
    public void findAll() {
        // 모든 제품 조회 테스트
        ProductForm product1 = new ProductForm("abc", 123, "www.test1.com");
        ProductForm product2 = new ProductForm("def", 456, "www.test2.com");

        repository.save(product1);
        repository.save(product2);

        List<Product> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2); // 조회된 제품 목록의 크기 검증
    }
}
