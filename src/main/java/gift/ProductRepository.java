package gift;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final class ProductRowMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setDescription(rs.getString("description"));
            return product;
        }
    }

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", new ProductRowMapper());
    }

    public Product findById(Long id) {
        return jdbcTemplate.query(
            "SELECT * FROM product WHERE id = ?",
            new Object[]{id},
            new ResultSetExtractor<Product>() {
                @Override
                public Product extractData(ResultSet rs) throws SQLException, DataAccessException {
                    if (rs.next()) {
                        Product product = new Product();
                        product.setId(rs.getLong("id"));
                        product.setName(rs.getString("name"));
                        product.setPrice(rs.getBigDecimal("price"));
                        product.setDescription(rs.getString("description"));
                        return product;
                    }
                    return null; // or throw an exception if the product is not found
                }
            }
        );
    }

    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO product (name, price, description) VALUES (?, ?, ?)",
            product.getName(), product.getPrice(), product.getDescription());
    }

    public void update(Product product) {
        jdbcTemplate.update("UPDATE product SET name = ?, price = ?, description = ? WHERE id = ?",
            product.getName(), product.getPrice(), product.getDescription(), product.getId());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }

}
