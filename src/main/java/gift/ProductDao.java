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
            create table customer (
              id bigint,
              name varchar(255),
              price money,
              imageUrl varchar(255),
              amount int,
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }

}
