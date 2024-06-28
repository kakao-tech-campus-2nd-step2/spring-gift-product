package gift.dao;

import gift.domain.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDao implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO product (name, price, image_url) VALUES(?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        sql,
                        (rs, rowNum) -> new Product(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getInt("price"),
                                rs.getString("image_url")
                        ),
                        id
                )
        );
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                )
        );
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void deleteAll() {
        String sql = "TRUNCATE TABLE product";
        jdbcTemplate.update(sql);
    }

    public int update(Long id, Product product) {
        String sql = """
                UPDATE product
                SET name = ?, price = ?, image_url = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }
}
