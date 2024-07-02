package gift.repository;

import gift.dto.ProductDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
    private final JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }

    public void createProductTable(){
        var sql = """
                create table product (
                    id bigint,
                    name varchar(255),
                    price bigint,
                    url varchar(255),
                    primary key(id)
                )
                """;
        jdbcTemplate.execute(sql);
    }

    public void insertProduct(ProductDTO productDTO) {
        var sql = "insert i nto product (id, name, price, url) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,productDTO.getId(), productDTO.getName(),productDTO.getPrice(),productDTO.getUrl());
    }

    public ProductDTO selectProduct(long id) {
        var sql = "select id, name, price, url from product where id= ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new ProductDTO(
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("url")
                ),
                id
        );
    }
}
