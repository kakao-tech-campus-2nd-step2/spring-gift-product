package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void createProductTable() {
        var sql = """
            create table product (
              id bigint,
              productName varchar(255),
              price money,
              imageUrl varchar(255),
              amount int,
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }
    public void insertProduct(Product product) {
        var sql = "insert into customer (id, productName, price, imageUrl, amount) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(),product.imageUrl(), product.amount());
    }
    public void deleteProduct(long id) {
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
