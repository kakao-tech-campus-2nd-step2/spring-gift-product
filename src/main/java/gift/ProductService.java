package gift;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, productRowMapper());
    }

    public Product getProductById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, productRowMapper(), id);
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.id(), product.name(), product.price(), product.imageUrl());
    }

    public void updateProduct(Long id, Product product) {
//        TODO
//        update DB 로직으로 수정
//        if(!id.equals(product.id())) {
//            throw new IllegalArgumentException(
//                "[ERROR] 요청한 상품의 ID와 상품 정보의 ID가 동일하지 않습니다."
//            );
//        }
//
//        if(!products.containsKey(id)) {
//            throw new IllegalArgumentException(
//                "[ERROR] ID가 " + product.id() + "인 상품을 찾을 수 없어 수정할 수 없습니다."
//            );
//        }
//
//        products.put(product.id(), product);
    }

    public void deleteProduct(Long id) {
//        TODO
//        삭제 DB 로직으로 변경
//        쿼리를 통해 검증/삭제 한번에 처리 가능
//        if(!products.containsKey(id)) {
//            throw new IllegalArgumentException(
//                "[ERROR] ID가 " + id + "인 상품을 찾을 수 없어 삭제할 수 없습니다."
//            );
//        }
//        products.remove(id);
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
            );
            return product;
        };
    }
}
