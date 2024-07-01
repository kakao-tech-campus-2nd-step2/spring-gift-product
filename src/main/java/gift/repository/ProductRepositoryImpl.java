package gift.repository;

import gift.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("price"),
        rs.getString("image_url")
    );

    @Autowired
    public ProductRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT * FROM `products` WHERE `id` = ?";
        try {
            Product product = jdbcTemplate.queryForObject(sql, rowMapper, id);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean exists(Long id) {
        String sql = "SELECT COUNT(`id`) FROM `products` WHERE `id` = ?";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return result != null && result > 0;
    }

    @Override
    public void save(Product product) {
        if (exists(product.getId())) {
            String sql = "UPDATE `products` SET `name` = ?, `price` = ?, `image_url` = ? WHERE `id` = ?";
            jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(),
                product.getId());
            return;
        }
        String sql = "INSERT INTO `products` (`name`, `price`, `image_url`) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM `products`";
        Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
        if (result == null) {
            return 0;
        }
        return result;
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM `products`";
        List<Product> products = jdbcTemplate.query(sql, rowMapper);
        return List.copyOf(products);
    }

    @Override
    public void remove(Long id) {
        String sql = "DELETE FROM `products` WHERE `id` = ?";
        jdbcTemplate.update(sql, id);
    }
}
