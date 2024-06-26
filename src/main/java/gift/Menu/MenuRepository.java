package gift.Menu;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MenuRepository {
    private final Map<Long,Menu> menus = new HashMap<>();
    private Long id = 1L;

    public Menu save(Menu menu){
        menu.setId(id);
        menus.put(id++,menu);
        return menu;
    }
    public Long delete(Long id){
        menus.remove(id);
        return id;
    }
    public List<Menu> findAll(){
        return menus.values()
                .stream()
                .toList();
    }

}
