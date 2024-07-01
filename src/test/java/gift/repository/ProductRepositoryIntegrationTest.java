package gift.repository;

import gift.model.ProductDAO;
import gift.model.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class ProductRepositoryIntegrationTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void save() {
        ProductDTO product = new ProductDTO("abc", 123, "test.com");
        ProductDAO savedProduct = productRepository.save(product);

        ProductDAO findProduct = productRepository.findById(savedProduct.getId());
        assertThat(product.getName()).isEqualTo(findProduct.getName());
        assertThat(product.getPrice()).isEqualTo(findProduct.getPrice());
        assertThat(product.getImageUrl()).isEqualTo(findProduct.getImageUrl());
    }

    @Test
    public void delete() {
        ProductDTO product = new ProductDTO("abc", 123, "test.com");
        ProductDAO savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct.getId());
        ProductDAO findProduct = productRepository.findById(savedProduct.getId());
        assertThat(findProduct).isEqualTo(null);
    }

    @Test
    public void edit() {
        ProductDTO product = new ProductDTO("abc", 123, "test.com");
        ProductDAO savedProduct = productRepository.save(product);

        ProductDTO editProductForm = new ProductDTO("def", product.getPrice(),
                product.getImageUrl());

        ProductDAO editProduct = productRepository.edit(savedProduct.getId(), editProductForm);
        ProductDAO findProduct = productRepository.findById(savedProduct.getId());
        assertThat(findProduct.getName()).isEqualTo(editProduct.getName());
    }

    @Test
    public void findAll() {
        ProductDTO product1 = new ProductDTO("abc", 123, "test1.com");
        ProductDTO product2 = new ProductDTO("def", 234, "test2.com");
        ProductDTO product3 = new ProductDTO("ghi", 345, "test3.com");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<ProductDAO> allProducts = productRepository.findAll();
        assertThat(allProducts.size()).isEqualTo(3);
    }

    @Test
    public void save_nameExceedingLengthLimit() {
        String wrongName = "a".repeat(256);
        ProductDTO product = new ProductDTO(wrongName, 123, "test.com");

        assertThatThrownBy(() -> productRepository.save(product))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
