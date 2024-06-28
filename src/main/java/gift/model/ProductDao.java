package gift.model;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> selectAllProduct(){
        var sql = "select id, name, price, image_url from products";
        return jdbcTemplate.query(
            sql,
            (resultSet, rowNum)->new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("image_url")
            )
        );
    }

    public Product selectProductById(Long id){
        var sql = "select id, name, price, image_url from products where id=?";
        return jdbcTemplate.queryForObject(
            sql,
            (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("image_url")
            ),
            id
        );
    }

    public void insertProduct(Product product){
        var sql = "insert into products (id, name, price, image_url) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void updateProductById(Long id, Product product){
        var sql = "update products set name=?, price=?, image_url=? where id=?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    public void deleteProductById(Long id){
        var sql = "DELETE FROM products where id=?";
        jdbcTemplate.update(sql, id);
    }
}
