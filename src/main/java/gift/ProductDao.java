package gift;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao{
    private final JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        createProductTable();
    }
    public void createProductTable(){
        var sql = """
            create table product(
                id bigint,
                name varchar(255),
                price int,
                imageUrl varchar(255),
                primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }
    public void insertProduct(Product product){
        var sql="insert into product (id, name, price, imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,product.id(),product.name(),product.price(),product.imageUrl());
    }
    public Product selectProduct(long id){
        var sql="select id, name, price, imageUrl from product where id = ?";
        return jdbcTemplate.queryForObject(
            sql,
            (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            ), id
        );
    }
    public void updateProduct(Product product){
        var sql = "update product set name=?, price=?, imageUrl=? WHERE id=?";
        jdbcTemplate.update(
            sql,
            product.name(),
            product.price(),
            product.imageUrl(),
            product.id());
    }
    public void deleteProduct(Long id){
        var sql = "delete from product where id = ?";
        jdbcTemplate.update(sql,id);
    }
    public List<Product> findAll(){
        var sql = "select * from product";
        return jdbcTemplate.query(sql,
            (resultSet, rowNum) -> new Product(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getInt("price"),
                resultSet.getString("imageUrl")
            ));
    }
}
