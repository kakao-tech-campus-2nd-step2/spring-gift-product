package gift.repository;

import gift.model.Product;
import gift.model.ProductForm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class JdbcProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcProductRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product save(ProductForm form) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("product").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", form.getName());
        parameters.put("price", form.getPrice());
        parameters.put("imageUrl", form.getImageUrl());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        Product product = new Product();
        product.setId((Long) key);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setImageUrl(form.getImageUrl());

        return product;
    }

    @Override
    public boolean delete(Long id) {
        int update = jdbcTemplate.update("delete from product where id = ?", id);
        return update != 0;
    }

    @Override
    public Product edit(Long id, ProductForm form) {
        jdbcTemplate.update("update product set name = ?, price = ?, imageUrl = ? where id = ?",
            form.getName(), form.getPrice(), form.getImageUrl(), id);
        return findById(id);
    }

    @Override
    public Product findById(Long id) {
        List<Product> result = jdbcTemplate.query("select * from product where id = ?",
            productRowMapper(), id);
        return result.stream().findFirst().orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("select * from product", productRowMapper());
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