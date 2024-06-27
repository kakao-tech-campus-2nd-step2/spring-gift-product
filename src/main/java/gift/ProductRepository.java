package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (id, name, price, image_url) VALUES (?, ?, ?, ?)";
    private static final String SELECT_ALL_PRODUCTS_SQL = "SELECT * FROM product";
    private static final String SELECT_PRODUCT_BY_ID_SQL = "SELECT * FROM product WHERE id = ?";
    private static final String UPDATE_PRODUCT_SQL = "UPDATE product SET name = ?, price = ?, image_url = ? WHERE id = ?";
    private static final String DELETE_PRODUCT_SQL = "DELETE FROM product WHERE id = ?";

}
