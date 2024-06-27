package gift.product.repository;

import gift.product.config.Config;
import gift.product.model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final Map<Long, Product> products = new HashMap<>();

    public ProductRepository() {
        jdbcTemplate = new Config().jdbcTemplate();
    }

    public void save(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        var sql = "INSERT INTO Product (name, price, imageUrl) VALUES (?, ?, ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(
                    sql, new String[]{"id"}
                );
                pstmt.setString(1, product.getName());
                pstmt.setInt(2, product.getPrice());
                pstmt.setString(3, product.getImageUrl());

                return pstmt;
            }
        }, keyHolder);

        product.setId(keyHolder.getKey().longValue());
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public void update(Product product) {
        Long id = product.getId();
        products.put(id, product);
    }

    public void delete(Long id) {
        products.remove(id);
    }
}
