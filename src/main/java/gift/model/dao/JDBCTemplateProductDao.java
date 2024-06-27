package gift.model.dao;

import gift.model.Product;
import gift.model.repository.ProductRepository;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCTemplateProductDao implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public JDBCTemplateProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTable();
    }


    private final String CREATE_PRODUCT_TABLE_QUERY = """
            CREATE TABLE IF NOT EXISTS products (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(255) NOT NULL,
                price int NOT NULL,
                image_url VARCHAR(255),
                is_deleted BOOLEAN DEFAULT FALSE
            );
        """;

    private final String INSERT_PRODUCT_QUERY = """
            INSERT INTO products (name, price, image_url, is_deleted) VALUES (?, ?, ?, ?);
        """;

    private final String SELECT_ALL_PRODUCTS_QUERY = """
            SELECT * FROM products;
        """;

    private final String SELECT_PRODUCT_BY_ID_QUERY = """
            SELECT * FROM products WHERE id = ?;
        """;

    private final String UPDATE_PRODUCT_QUERY = """
            UPDATE products SET name = ?, price = ?, image_url = ?, is_deleted = ? WHERE id = ?;
        """;

    private final String DELETE_PRODUCT_QUERY = """
            DELETE FROM products WHERE id = ?;
        """;

    @Override
    public void save(Product entity) {
        if (entity.isNew()) {
            jdbcTemplate.update(INSERT_PRODUCT_QUERY, entity.getName(), entity.getPrice(), entity.getImgUrl(), entity.isDeleted());
        } else {
            update(entity);
        }
    }
    @Override
    public void update(Product entity) {
        jdbcTemplate.update(UPDATE_PRODUCT_QUERY, entity.getName(), entity.getPrice(), entity.getImgUrl(), entity.isDeleted(), entity.getId());
    }

    @Override
    public Product find(Long id) {
        return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID_QUERY, new Object[]{id}, ((rs, rowNum) -> {
            return new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image_url"),
                    rs.getBoolean("is_deleted"));
        }));
    }

    @Override
    public void delete(Product entity) {
        jdbcTemplate.update(DELETE_PRODUCT_QUERY, entity.getId());
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS_QUERY, (rs, rowNum) -> {
            return new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("image_url"),
                    rs.getBoolean("is_deleted"));
        });
    }

    private void createTable() {
        jdbcTemplate.execute(CREATE_PRODUCT_TABLE_QUERY);
        System.out.println("Table created");
    }
}
