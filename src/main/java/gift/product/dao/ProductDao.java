package gift.product.dao;

import gift.product.model.ProductVo;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    private final RestClientAutoConfiguration restClientAutoConfiguration;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate,
        RestClientAutoConfiguration restClientAutoConfiguration) {
        this.jdbcTemplate = jdbcTemplate;
        this.restClientAutoConfiguration = restClientAutoConfiguration;
    }

    public void createProductTable() {
        var sql = """
            create table product_list (
              id bigint,
              name varchar(255),
              price int,
              imageUrl varchar(255),
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

    public void addProduct(Connection connection, ProductVo productVo) throws Exception {
        var sql = "insert into product_list (id, name, price, imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            productVo.getId(),
            productVo.getName(),
            productVo.getPrice(),
            productVo.getImageUrl()
        );
    }

    public ProductVo selectProduct(Connection connection, long id) throws Exception {
        var sql = "select id, name, price, imageUrl from product_list where id = ?";
        var statement = connection.prepareStatement(sql);
        statement.setLong(1, id);
        var resultSet = statement.executeQuery();
        ProductVo product = new ProductVo();
        if(resultSet.next()) {
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getInt("price"));
            product.setImageUrl(resultSet.getString("imageUrl"));
            return product;
        }
        resultSet.close();
        statement.close();
        return null;
    }

    public void deleteProduct(long id) {
        var sql = "delete from product_list where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void modifyProduct(ProductVo productVo) {
        var sql = "update product_list set name = ?, price = ?, imageUrl = ? where id = ?";
        jdbcTemplate.update(
            sql,
            productVo.getName(),
            productVo.getPrice(),
            productVo.getImageUrl(),
            productVo.getId()
        );
    }
}
