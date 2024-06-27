package gift.product.repository;

import gift.product.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> findAllProducts() {
        String sql = "select * from product";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    @Override
    public Product findProductById(Long id) {
        String sql = "select * from product where id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper(), id);
    }

    @Override
    public Product createProduct(Product product) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("product")
            .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("imageUrl", product.getImageUrl());

        Long id = jdbcInsert.executeAndReturnKey(parameters).longValue();
        return findProductById(id);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        String sql = "update product set name = ?, price = ?, imageUrl = ? where id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        return findProductById(id);
    }

    @Override
    public boolean isExist(Long id) {
        String sql = "select exists (select * from product where id = ?)";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, Boolean.class);
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
}
