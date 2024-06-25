package Menu;

import org.springframework.stereotype.Service;

@Service
public class MenuService {
    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public Long save(String name,int price, String imageUrl){
        Menu menu = new Menu(name,price,imageUrl);
        return menuRepository.save(menu)
                .getId();
    }

}
