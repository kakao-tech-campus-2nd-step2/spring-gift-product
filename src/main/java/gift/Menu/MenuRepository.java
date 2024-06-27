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

    public Menu update(Long id, String name, int price, String imageUrl) {
        Menu updatedMenu = menus.get(id);
        updatedMenu.update(name,price,imageUrl);
        return updatedMenu;
    }

    public Menu findById(Long id) {
        return menus.get(id);
    }
}
