package gift.repository;

import static org.assertj.core.api.Assertions.assertThat;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ProductRepositoryIntegrationTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void save() {
        // 제품 저장 테스트
        ProductForm productForm = new ProductForm("abc", 123, "test.com");
        Product savedProduct = productRepository.save(productForm);

        // 저장된 제품 조회
        Product findProduct = productRepository.findById(savedProduct.getId());

        // 저장된 제품과 조회된 제품 비교
        assertThat(findProduct.getName()).isEqualTo(productForm.getName());
        assertThat(findProduct.getPrice()).isEqualTo(productForm.getPrice());
        assertThat(findProduct.getImageUrl()).isEqualTo(productForm.getImageUrl());
    }

    @Test
    public void delete() {
        // 제품 삭제 테스트
        ProductForm productForm = new ProductForm("abc", 123, "test.com");
        Product savedProduct = productRepository.save(productForm);

        // 제품 삭제
        productRepository.delete(savedProduct.getId());

        // 삭제된 제품 조회
        Product findProduct = productRepository.findById(savedProduct.getId());

        // 삭제된 제품이 null인지 검증
        assertThat(findProduct).isNull();
    }

    @Test
    public void edit() {
        // 제품 수정 테스트
        ProductForm productForm = new ProductForm("abc", 123, "test.com");
        Product savedProduct = productRepository.save(productForm);

        // 수정할 제품 정보 설정
        ProductForm editProductForm = new ProductForm("def", productForm.getPrice(),
            productForm.getImageUrl());

        // 제품 수정
        Product editedProduct = productRepository.edit(savedProduct.getId(), editProductForm);

        // 수정된 제품 조회
        Product findProduct = productRepository.findById(savedProduct.getId());

        // 수정된 제품의 이름이 예상과 일치하는지 검증
        assertThat(findProduct.getName()).isEqualTo(editedProduct.getName());
    }

    @Test
    public void findAll() {
        // 모든 제품 조회 테스트
        ProductForm product1 = new ProductForm("abc", 123, "test1.com");
        ProductForm product2 = new ProductForm("def", 234, "test2.com");
        ProductForm product3 = new ProductForm("ghi", 345, "test3.com");

        // 제품 저장
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        // 모든 제품 조회
        List<Product> allProducts = productRepository.findAll();

        // 조회된 제품 목록의 크기 검증
        assertThat(allProducts.size()).isEqualTo(3);
    }
}
