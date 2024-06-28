package gift.dao;

import gift.entity.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

  private final JdbcTemplate jdbcTemplate;

  public ProductDao(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
      rs.getLong("id"),
      rs.getString("name"),
      rs.getInt("price"),
      rs.getString("imageUrl")
  );

  public List<Product> findAll() {
    return jdbcTemplate.query("SELECT * FROM product", rowMapper);
  }

  public Product findById(long id) {
    return jdbcTemplate.queryForObject("SELECT * FROM product WHERE id = ?", new Object[]{id}, rowMapper);
  }

  public int save(Product product) {
    return jdbcTemplate.update("INSERT INTO product (name, price, imageUrl) VALUES (?, ?, ?)",
        product.getName(), product.getPrice(), product.getImageUrl());
  }

  public int update(Product product) {
    return jdbcTemplate.update("UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
        product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
  }

  public int deleteById(long   id) {
    return jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
  }


}
