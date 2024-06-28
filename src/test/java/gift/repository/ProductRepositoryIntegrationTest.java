package gift.repository;
public class ProductRepositoryIntegrationTest {

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
        ProductForm product = new ProductForm("abc", 123, "test.com");
        Product savedProduct = productRepository.save(product);

        Product findProduct = productRepository.findById(savedProduct.getId());
        assertThat(product.getName()).isEqualTo(findProduct.getName());
        assertThat(product.getPrice()).isEqualTo(findProduct.getPrice());
        assertThat(product.getImageUrl()).isEqualTo(findProduct.getImageUrl());
    }

    @Test
    public void delete() {
        ProductForm product = new ProductForm("abc", 123, "test.com");
        Product savedProduct = productRepository.save(product);

        productRepository.delete(savedProduct.getId());
        Product findProduct = productRepository.findById(savedProduct.getId());
        assertThat(findProduct).isEqualTo(null);
    }

    @Test
    public void edit() {
        ProductForm product = new ProductForm("abc", 123, "test.com");
        Product savedProduct = productRepository.save(product);

        ProductForm editProductForm = new ProductForm("def", product.getPrice(),
            product.getImageUrl());

        Product editProduct = productRepository.edit(savedProduct.getId(), editProductForm);
        Product findProduct = productRepository.findById(savedProduct.getId());
        assertThat(findProduct.getName()).isEqualTo(editProduct.getName());
    }

    @Test
    public void findAll() {
        ProductForm product1 = new ProductForm("abc", 123, "test1.com");
        ProductForm product2 = new ProductForm("def", 234, "test2.com");
        ProductForm product3 = new ProductForm("ghi", 345, "test3.com");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> allProducts = productRepository.findAll();
        assertThat(allProducts.size()).isEqualTo(3);
    }
}