package gift.web;

import gift.web.dto.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createProductTable() {
        var sql = """
            create table products (
                id bigint,
                name varchar(255),
                price bigint,
                image_url varchar(255),
                primary key (id) 
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public void insertProduct(Product product) {
        var sql = "insert into products (id, name, price, image_url) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
    }

    public List<Product> selectAllProducts() {
        var sql = "select * from products";
        return jdbcTemplate.query(
            sql,
            (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
            )
        );
    }
    public Product selectProductById(long id) {
        var sql = "select * from products where id = ?";
        return jdbcTemplate.queryForObject(
            sql,
            (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("image_url")
            ),
            id
        );
    }

    public void updateProduct(Product product) {
        var sql = "update products set name = ?, price = ?, image_url = ? where id = ?";
        jdbcTemplate.update(
            product.name(),
            product.price(),
            product.imageUrl()
            product.id()
        );
    }

    public void deleteProductById(long id) {
        var sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
