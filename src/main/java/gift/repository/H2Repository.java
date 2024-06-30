package gift.repository;

import gift.dto.ProductDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class H2Repository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public H2Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<ProductDTO> getProducts() {
        var sql = "SELECT * FROM PRODUCT";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    public ProductDTO getProduct(Long id) {
        var sql = "SELECT * FROM PRODUCT WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper(), id);
    }

    public void addProduct(ProductDTO productDTO) {
        var sql = "INSERT INTO PRODUCT (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, productDTO.name(), productDTO.price(), productDTO.imageUrl());
    }

    public void updateProduct(long id, ProductDTO productDTO) {
        var sql = "UPDATE PRODUCT SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, productDTO.name(), productDTO.price(), productDTO.imageUrl(), id);
    }

    public void deleteProduct(long id) {
        var sql = "DELETE FROM PRODUCT WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    private RowMapper<ProductDTO> productRowMapper() {
        return (resultSet, rowNum) -> new ProductDTO(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getInt("price"),
            resultSet.getString("imageUrl"));
    }
}
