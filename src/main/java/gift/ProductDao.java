package gift;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    @Autowired
    private JdbcClient jdbcClient;

    public List<Product> getAllProducts() {
        return jdbcClient.sql("select * from product")
                    .query(Product.class)
                    .list();
    }

    public void insert(Product product) {
        jdbcClient.sql("insert into product (name, price, imageUrl) values (?,?,?)")
            .param(List.of(product.name(), product.price(), product.imageUrl()))
            .update();
    }
}
