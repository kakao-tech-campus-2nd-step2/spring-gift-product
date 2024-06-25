package gift.repository;

import static org.assertj.core.api.Assertions.assertThat;

import gift.Product;
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
        Product product = new Product("abc", 123, "www.test.com");

        repository.save(product);

        Product result = repository.findById(product.getId());

        assertThat(result).isEqualTo(product);
    }

    @Test
    @DisplayName("existing id")
    public void delete_exist() {
        Product product = new Product("abc", 123, "www.test.com");
        repository.save(product);

        boolean result = repository.delete(product.getId());

        assertThat(result).isEqualTo(true);
    }

    @Test
    @DisplayName("nonexistent id")
    public void delete_nonexistent() {
        Product product = new Product("abc", 123, "www.test.com");
        repository.save(product);

        boolean result = repository.delete(0L);

        assertThat(result).isEqualTo(false);
    }

    @Test
    public void edit() {
        Product product1 = new Product("abc", 123, "www.test.com");
        repository.save(product1);

        boolean result = repository.edit(product1.getId(), new Product("def", 123, "www.test.com"));
        Product product2 = repository.findById(product1.getId());

        assertThat(result).isEqualTo(true);
        assertThat(product2.getName()).isEqualTo("def");
    }

    @Test
    public void findAll() {
        Product product1 = new Product("abc", 123, "www.test1.com");
        Product product2 = new Product("def", 456, "www.test2.com");

        repository.save(product1);
        repository.save(product2);

        List<Product> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
