package gift.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class GiftDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(Gift gift) {
        String sql = "INSERT INTO gift (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, gift.getName(), gift.getPrice(), gift.getImageUrl());
    }

    public Gift findById(Long id) {
        String sql = "SELECT * FROM gift WHERE id = ?";
        List<Gift> results = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Gift(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl")
                ), id);

        if (results.isEmpty()) {
            return null; // 또는 예외 처리를 수행할 수 있음
        } else {
            return results.get(0);
        }
    }


    public List<Gift> findAll() {
        String sql = "SELECT * FROM gift";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Gift(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("imageUrl")
                ));
    }

    public void updateById(Gift gift, Long id) {
        String sql = "UPDATE gift SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, gift.getName(), gift.getPrice(), gift.getImageUrl(), id);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM gift WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
