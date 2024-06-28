package gift.Repository;

import gift.Model.Item;
import gift.Model.ItemDTO;
import java.util.List;
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

    }

    @Override
    public Item findById(Long id) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public void update(Long id, ItemDTO itemDTO) {

    }

    @Override
    public void delete(Long id) {

    }
}
