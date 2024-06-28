package gift.Repository;

import gift.Model.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = (resultSet, rowNum) ->
        new Product(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            resultSet.getLong("price"),
            resultSet.getString("imageUrl")
        );

    public List<Product> checkProductsAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, productRowMapper);
    }

    public Product checkProductsById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        List<Product> products = jdbcTemplate.query(sql, new Object[]{id}, productRowMapper);
        return products.isEmpty() ? null : products.get(0);
    }

    public Product saveProduct(Product product) {
        String sql = "INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
        return product;
    }

    public void deleteProduct(long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateProduct(Product product, long id) {
        String sql = "UPDATE products SET id = ?, name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl(), id);
    }

}
