package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Product product) {
        String sql = "INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public Product select(Long id) {
        String sql = "SELECT id, name, price, imageUrl from products WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper(), id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Product> selectAll() {
        String sql = "SELECT id, name, price, imageUrl from products";
        List<Product> products = jdbcTemplate.query(sql, productRowMapper());
        return products;
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
            product.setImageUrl(rs.getString("imageUrl"));
            return product;
        };
    }

    public void update(Long id, Product product) {
        String sql = "UPDATE products SET name=?, price=?, imageUrl=? where id=?";
        jdbcTemplate.update(sql,
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                id
        );
    }

}
