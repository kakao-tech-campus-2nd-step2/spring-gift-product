package gift;

import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("product")
            .usingGeneratedKeyColumns("id");
    }

    public Optional<Product> findById(int id) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM products WHERE id = ?",
            new Object[]{id}, (resultSet, rowNum) ->
            new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imgUrl")
            ));
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products",
            (resultSet, rowNum) ->
                new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("imgUrl")
                ));
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}
