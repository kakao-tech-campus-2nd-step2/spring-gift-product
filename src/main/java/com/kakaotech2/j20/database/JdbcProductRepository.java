package com.kakaotech2.j20.database;

import com.kakaotech2.j20.Product;
import java.sql.PreparedStatement;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcProductRepository {
    private final JdbcTemplate template;

    public JdbcProductRepository(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        createTable();
    }

    private void createTable() {
        template.update("create table if not exists product("
            + "id long primary key auto_increment, "
            + "name varchar(255), "
            + "price int,"
            + "imageUrl varchar(255))");
    }

    public Product insertProduct(Product product) {
        String sql = "insert into product (name, price, imageUrl) values (?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql,new String[]{"id"});
            ps.setString(1, product.getName());
            ps.setInt(2, product.getPrice());
            ps.setString(3, product.getImageUrl());
            return ps;
        },keyHolder);
        long key = keyHolder.getKey().longValue();
        product.setId(key);
        return product;
    }

    public void updateProduct(Long id,Product product) {
        String sql = "update product set name = ?, price = ?, imageUrl = ? where id = ?";
        template.update(sql,product.getName(),product.getPrice(),product.getImageUrl(),id);
    }

    public Product getProduct(Long id) {
        String sql = "select * from product where id = ?";
        return template.queryForObject(sql,productRowMapper(),id);
    }

    public List<Product> findAllProducts() {
        String sql = "select * from product";
        return template.query(sql,productRowMapper());
    }

    public void deleteProduct(Long id) {
        String sql = "delete from product where id = ?";
        template.update(sql,id);
    }


    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> new Product(rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("imageUrl"));

    }

}
