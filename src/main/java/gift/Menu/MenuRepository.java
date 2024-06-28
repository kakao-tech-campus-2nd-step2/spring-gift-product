package gift.Menu;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuRepository{
    private final Map<Long,Menu> menus = new HashMap<>();
    private Long id = 1L;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MenuRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Menu save(Menu menu){
        var sql = "insert into menus (id, name, price,imageUrl) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, id++, menu.getName(), menu.getPrice(),menu.getImageUrl());
        return menu;
    }
    public Long delete(Long id){
        var sql = "delete from menus where id = ?";
        jdbcTemplate.update(sql, id);
        return id;
    }
    public List<Menu> findAll(){
        String sql = "select id, name, price,imageUrl from menus";
        List<Menu> menus = jdbcTemplate.query(
                sql, (resultSet, rowNum) -> {
                    Menu menu = new Menu(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("price"),
                            resultSet.getString("imageUrl")
                    );
                    return menu;
                });
        return menus;
    }

    public Menu update(Long id, String name, int price, String imageUrl) {
        String sql = "UPDATE menus SET name = ?, price = ?,imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, name,price,imageUrl,id);
        Menu updatedMenu = new Menu(id,name,price,imageUrl);
        return updatedMenu;
    }

    public Menu findById(Long id) {
        String sql = "select id, name, price,imageUrl from menus where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Menu(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl")
                ),
                id
        );
    }
}
