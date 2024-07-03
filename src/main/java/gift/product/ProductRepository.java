package gift.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertActor;
    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.insertActor = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Products")
                .usingGeneratedKeyColumns("id");
    }

    public Product insertProduct(Product product){
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("name", product.name());
        parameters.put("price", product.price());
        parameters.put("imageUrl", product.imageUrl());
        Long id = insertActor.executeAndReturnKey(parameters).longValue();
        return new Product(id, product.name(), product.price(), product.imageUrl());
    }
    public Product selectProduct(Long id) {
        var sql = "select id, name, price, imageUrl from Products where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price"),
                        resultSet.getString("imageUrl")
                ),
                id
        );
    }

    public List<Product> selectProducts(){
        var sql = "select id, name, price, imageUrl from Products";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getLong("price"),
                        resultSet.getString("imageUrl")
                )
        );
    }

    public Product updateProduct(Long id, Product updateParam){
        var sql = "update Products set name=?, price=?, imageUrl=? where id = ?";
        jdbcTemplate.update(sql,
                updateParam.name(),
                updateParam.price(),
                updateParam.imageUrl(),
                id);
        return new Product(id, updateParam.name(), updateParam.price(), updateParam.imageUrl());
    }

    public void deleteProduct(Long id){
        var sql = "delete from Products where id = ?";
        jdbcTemplate.update(sql, id);
    }


}