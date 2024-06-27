package gift.Menu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
@RequestMapping("/menu")
public class MenuController {
    MenuRepository menuRepository = new MenuRepository();
    MenuService menuService = new MenuService(menuRepository);

    @PostMapping
    public String save(
            @ModelAttribute MenuRequest request
    ) {
        Menu newMenu = menuService.save(request.name(),request.price(),request.imageUrl());
        return "redirect:/menu";
    }

    @GetMapping
    public List<Menu> read(Model model){
        List<Menu> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return menus;
    }

    @DeleteMapping("/{id}")
    public Long delete(
            @PathVariable("id") Long id
    ){
        Long deletedId = menuService.delete(id);
        return deletedId;
    }

    @PutMapping("/{id}")
    public Menu update(
            @PathVariable("id") Long id,
            @RequestBody MenuRequest request
    ){
        Menu updatedMenu = menuService.update(
                id,
                request.name(),
                request.price(),
                request.imageUrl()
        );
        return updatedMenu;
    }


}
