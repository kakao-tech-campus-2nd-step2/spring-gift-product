package gift;

import java.sql.Types;
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

    public void insert(ProductDto productDto) {
        jdbcClient.sql("insert into product (name, price, imageUrl) values (:name, :price, :imageUrl)")
            .param("name", productDto.getName(), Types.VARCHAR)
            .param("price", productDto.getPrice(), Types.INTEGER)
            .param("imageUrl", productDto.getImageUrl(), Types.VARCHAR)
            .update();
    }

    public void update(long id, Product product) {
        jdbcClient.sql("update product set name = ?, price = ?, imageUrl = ? where id = ?")
            .param(List.of(product.name(), product.price(), product.imageUrl(), id))
            .update();
    }

    public void delete(long id) {
        jdbcClient.sql("delete from product where id = :id")
            .param("id", id)
            .update();
    }
}
