package Menu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/menu")
public class MenuController {
    MenuRepository menuRepository = new MenuRepository();
    MenuService menuService = new MenuService(menuRepository);

    @PostMapping
    public Menu save(
            @RequestBody MenuRequest request
    ) {
        Menu newMenu = menuService.save(request.name(),request.price(),request.imageUrl());
        return newMenu;
    }


}
