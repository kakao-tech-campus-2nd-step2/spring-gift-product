package gift.repository;

import gift.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert insertProduct;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertProduct = new SimpleJdbcInsert(jdbcTemplate)
            .withTableName("product")
            .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setPrice(rs.getInt("price"));
        product.setImageUrl(rs.getString("imageUrl"));
        return product;
    };

    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", productRowMapper);
    }

    public Optional<Product> findById(long id) {
        List<Product> products = jdbcTemplate.query("SELECT * FROM product WHERE id = ?", new Object[]{id}, productRowMapper);
        return products.isEmpty() ? Optional.empty() : Optional.of(products.get(0));
    }

    public void save(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("imageUrl", product.getImageUrl());
        Number newId = insertProduct.executeAndReturnKey(parameters);
        product.setId(newId.longValue());
    }

    public void update(Product product) {
        jdbcTemplate.update("UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
            product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}
