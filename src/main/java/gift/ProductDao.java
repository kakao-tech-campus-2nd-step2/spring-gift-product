package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Types;

@Repository
public class ProductDao {

    @Autowired
    private JdbcClient jdbcClient;

    public Integer insertNewProduct(Product newProduct) {
        String sql = """
                    INSERT INTO product (id, name, price, imageUrl)
                    VALUES (:id, :name, :price, :imageUrl);
                """;
        return jdbcClient.sql(sql)
                .param("id", newProduct.id(), Types.BIGINT)
                .param("name", newProduct.name(), Types.VARCHAR)
                .param("price", newProduct.price(), Types.BIGINT)
                .param("imageUrl", newProduct.imageUrl(), Types.VARCHAR)
                .update();
    }
}