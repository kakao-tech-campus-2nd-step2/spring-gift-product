package gift.dao;

import gift.vo.Product;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * JdbcClient를 이용한 DB 접속
 */
@Repository
public class ProductDao {

    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Product> getProducts() {
        String sql = "SELECT * FROM product";
        return jdbcClient.sql(sql).query(new ProductMapper()).list();
    }

}
