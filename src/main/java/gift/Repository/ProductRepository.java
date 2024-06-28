package gift.Repository;

import gift.Model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> checkAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, (resultSet, rowNum) ->
            new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("price"),
                resultSet.getString("imageUrl")
            )
        );
    }
}
