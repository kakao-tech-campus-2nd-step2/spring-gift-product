package gift.repository;

import gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("imageUrl")
    );

    @Override
    public List<Product> findAll() {
        var sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    @Override
    public Product findById(Long id) {
        var sql = "SELECT * FROM product WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public void save(Product product) {
        var sql = "INSERT INTO product (name, price, imageUrl) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getImageUrl());
            return ps;
        }, keyHolder);

        product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    }

    @Override
    public void update(Product product) {
        var sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    @Override
    public void delete(Long id) {
        var sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> findPaginated(int page, int size) {
        int start = page * size;
        var sql = "SELECT * FROM product ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, productRowMapper, size, start);
    }
}
