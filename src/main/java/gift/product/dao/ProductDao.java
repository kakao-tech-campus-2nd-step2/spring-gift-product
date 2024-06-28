package gift.product.dao;

import gift.product.model.ProductVo;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
        var statement = connection.prepareStatement(sql);
        statement.setLong(1, productVo.getId());
        statement.setString(2, productVo.getName());
        statement.setInt(3, productVo.getPrice());
        statement.setString(4, productVo.getImageUrl());
        statement.execute();
        statement.close();
    }

}
