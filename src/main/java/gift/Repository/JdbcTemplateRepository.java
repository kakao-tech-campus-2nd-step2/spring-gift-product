package gift.Repository;

import gift.Model.Item;
import gift.Model.ItemDTO;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateRepository implements ItemRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void insert(ItemDTO itemDTO) {
        var sql = "insert into item (name,price,imgUrl) values (?,?,?)";
        jdbcTemplate.update(sql,itemDTO.getName(),itemDTO.getPrice(),itemDTO.getImgUrl());
    }

    @Override
    public Item findById(Long id) {
        try {
            Item item = jdbcTemplate.queryForObject(
                "select * from item where id =?",
                new Object[]{id},
                new BeanPropertyRowMapper<>(Item.class)
            );
            return item;
        }catch (Exception e){return null;}
    }

    @Override
    public List<Item> findAll() {
        return jdbcTemplate.query(
            "select* from item",
            new BeanPropertyRowMapper<Item>(Item.class)
        );
    }

    @Override
    public void update(Long id, ItemDTO itemDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
