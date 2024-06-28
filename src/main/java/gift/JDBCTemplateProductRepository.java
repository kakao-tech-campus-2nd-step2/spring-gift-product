package gift;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCTemplateProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;
    private final AtomicLong sequence = new AtomicLong();

    public JDBCTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String sql = """
            create table product(
               id BIGINT,
               name varchar(255),
               price int,
               imageUrl varchar(255),
               primary key(id) 
            )
            """;
        jdbcTemplate.execute(sql);
    }

    @Override
    public Product insert(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }
}
