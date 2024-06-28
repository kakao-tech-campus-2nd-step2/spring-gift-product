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
            create table products (
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
        var sql = "insert into products (id, productName, price, imageUrl, amount) values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(),product.imageUrl(), product.amount());
    }
    public void deleteProduct(long id) {
        var sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }
    public void updateProduct(Product product){
        var sql = "update products set productName = ? , price = ?, imageUrl = ? amount = ? where id = ? ";
        jdbcTemplate.update(sql, product.name(), product.price(),product.imageUrl(), product.amount(), product.id());
    }
}
