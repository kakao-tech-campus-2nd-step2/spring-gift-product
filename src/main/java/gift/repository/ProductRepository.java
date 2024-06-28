package gift.repository;

import gift.domain.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product selectProductById(Long id) {
        String sql = "select * from products where id = ?";
        Product p =  jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("price"),
                resultSet.getString("imageUrl")
            ),
            id
        );
        System.out.println(p);
        return p;
    }

    public List<Product> selectAllProducts() {
        String sql = "select * from products";
        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("price"),
                resultSet.getString("imageUrl")
            )
        );
    }

    public void insertProduct(Product product) {
        String sql = "insert into products (id, name, price, imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
    }

    public void updateProduct(Product product) {
        String sql = "update products set name = ?, price = ?, imageUrl = ? where id = ?";
        jdbcTemplate.update(sql, product.name(), product.price(), product.imageUrl(), product.id());
    }

    public void deleteProductById(Long id) {
        String sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Long getNextId() {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 FROM products";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
