package gift.Menu;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Menu save(String name, int price, String imageUrl) {
        Menu menu = new Menu(name, price, imageUrl);
        return menuRepository.save(menu);
    }

    public List<Menu> findall() {
        List<Menu> menus = menuRepository.findAll();
        return menus;
    }

    public Long delete(Long id) {
        Long deletedId = menuRepository.delete(id);
        return id;
    }

    public void update(Long id, String name, int price, String imageUrl) {
        menuRepository.update(id, name, price, imageUrl);
    }

    public Menu findById(Long id) {
        Menu menu = menuRepository.findById(id);
        return menu;
    }
}
