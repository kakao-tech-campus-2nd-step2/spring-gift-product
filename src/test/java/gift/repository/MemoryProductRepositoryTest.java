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
        repository.clearDB();
    }

    @Test
    public void save() {
        ProductForm product = new ProductForm("abc", 123, "www.test.com");

        Product savedProduct = repository.save(product);

        Product result = repository.findById(savedProduct.getId());

        assertThat(result).isEqualTo(savedProduct);
    }

    @Test
    @DisplayName("existing id")
    public void delete_exist() {
        ProductForm product = new ProductForm("abc", 123, "www.test.com");
        Product savedProduct = repository.save(product);

        boolean result = repository.delete(savedProduct.getId());

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("nonexistent id")
    public void delete_nonexistent() {
        ProductForm product = new ProductForm("abc", 123, "www.test.com");
        repository.save(product);

        boolean result = repository.delete(0L);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void edit() {
        ProductForm product1 = new ProductForm("abc", 123, "www.test.com");
        Product savedProduct1 = repository.save(product1);

        boolean result = repository.edit(savedProduct1.getId(),
            new ProductForm("def", 123, "www.test.com"));
        Product product2 = repository.findById(savedProduct1.getId());

        assertThat(result).isEqualTo(true);
        assertThat(product2.getName()).isEqualTo("def");
    }

    @Test
    public void findAll() {
        ProductForm product1 = new ProductForm("abc", 123, "www.test1.com");
        ProductForm product2 = new ProductForm("def", 456, "www.test2.com");

        repository.save(product1);
        repository.save(product2);

        List<Product> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
