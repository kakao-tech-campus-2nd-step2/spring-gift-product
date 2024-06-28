package gift.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("product")
            .usingGeneratedKeyColumns("id");
    }

    public Product save(ProductRequest productRequest) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", productRequest.name());
        parameters.put("price", productRequest.price());
        parameters.put("imageUrl", productRequest.imageUrl());

        Number key = jdbcInsert.executeAndReturnKey(parameters);
        long id = key.longValue();

        Product product = findById(id);
        return product;
    }

    public Product findById(Long id) {
        return null;
    }

    public List<Product> findAll() {
        return null;
    }

    public Product update(Long id, ProductRequest productRequest) {
        return null;
    }

    public void delete(Long id) {

    }
}
