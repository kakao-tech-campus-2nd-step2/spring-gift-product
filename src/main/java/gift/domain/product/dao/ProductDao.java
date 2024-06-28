package gift.domain.product.dao;

import gift.domain.product.dto.ProductDto;
import gift.domain.product.entity.Product;
import java.util.List;
import java.util.Objects;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

    private final JdbcClient jdbcClient;

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Product insert(Product product) {
        String sql = "INSERT INTO product (name, price, image_url) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql).param(product.getName()).param(product.getPrice()).param(product.getImageUrl())
            .update(keyHolder);

        product.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());

        return product;
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM product";

        return jdbcClient.sql(sql).query(ProductDto.class).stream().map(ProductDto::toProduct).toList();
    }
}