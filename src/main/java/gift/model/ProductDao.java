package gift.model;

import gift.controller.ProductRequest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class ProductDao {
    private final JdbcClient jdbcClient;
    private final Map<Long, Product> products = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public ProductDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Product updateById(long id, ProductRequest request) {
        Product newProduct = new Product(id, request.name(), request.price(), request.imageUrl());
        products.replace(id, newProduct);
        return newProduct;
    }

    public void save(ProductRequest request) {
        long id = idGenerator.incrementAndGet();
        Product product = new Product(id, request.name(), request.price(), request.imageUrl());
        products.put(id, product);
    }

    public Product findById(long id) {
        return products.get(id);
    }


    public List<Product> findAll() {
        var sql = "select * from product";
        return jdbcClient.sql(sql)
                .query(Product.class)
                .list();
    }

    public void deleteById(long id) {
        var sql = "delete from product where id = ?";
        jdbcClient.sql(sql)
                .params(id)
                .update();
    }
}
