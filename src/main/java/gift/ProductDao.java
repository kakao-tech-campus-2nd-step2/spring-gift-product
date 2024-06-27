package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.List;

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

    public List<Product> selectProducts() {
        String sql = "SELECT * FROM product;";
        return jdbcClient.sql(sql)
                .query((rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("price"),
                        rs.getString("imageUrl")
                        ))
                .list();
    }

    public Product selectOneProduct(Long id) {
        String sql = "SELECT * FROM product WHERE id = :id;";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query((rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("price"),
                        rs.getString("imageUrl")
                ))
                .single();
    }
}