package gift.repository;

import gift.model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getString("name"),
        rs.getLong("price"),
        rs.getString("imageUrl")
    );

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products", productRowMapper);
    }

    public Product findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?", new Object[]{id}, productRowMapper);
    }

    public int save(Product product) {
        return jdbcTemplate.update(
            "INSERT INTO products (name, price, imageUrl) VALUES (?, ?, ?)",
            product.getName(), product.getPrice(), product.getImageUrl()
        );
    }

    public int update(Long id, Product product) {
        return jdbcTemplate.update(
            "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
            product.getName(), product.getPrice(), product.getImageUrl(), id
        );
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }


}
