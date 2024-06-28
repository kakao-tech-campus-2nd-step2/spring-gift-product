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

    public Product checkById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{id}, (resultSet, rowNum) ->
            new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("price"),
                resultSet.getString("imageUrl")
            )
        );
        return products.isEmpty() ? null : products.get(0);
    }

    public Product save(Product product) {
        String sql = "INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
        return product;
    }

    public void delete(long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


}
