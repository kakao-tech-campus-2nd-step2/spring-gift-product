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

    @Override
    public void save(Product entity) {
        if (entity.isNew()) {
            jdbcTemplate.update(ProductQuery.INSERT_PRODUCT.getQuery(), entity.getName(), entity.getPrice(), entity.getImgUrl(), entity.isDeleted());
        } else {
            update(entity);
        }
    }
    @Override
    public void update(Product entity) {
        jdbcTemplate.update(ProductQuery.UPDATE_PRODUCT.getQuery(), entity.getName(), entity.getPrice(), entity.getImgUrl(), entity.isDeleted(), entity.getId());
    }

    @Override
    public Product find(Long id) {
        return jdbcTemplate.queryForObject(ProductQuery.SELECT_PRODUCT_BY_ID.getQuery(), new Object[]{id}, ((rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url"),
                rs.getBoolean("is_deleted"))));
    }

    @Override
    public void delete(Product entity) {
        jdbcTemplate.update(ProductQuery.DELETE_PRODUCT.getQuery(), entity.getId());
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(ProductQuery.SELECT_ALL_PRODUCTS.getQuery(), (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("image_url"),
                rs.getBoolean("is_deleted")));
    }

    private void createTable() {
        jdbcTemplate.execute(ProductQuery.CREATE_PRODUCT_TABLE.getQuery());
        System.out.println("Table created");
    }
}