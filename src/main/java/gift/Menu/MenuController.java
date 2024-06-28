package gift.Menu;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;
    public MenuController(MenuService menuService){
        this.menuService = menuService;
    }

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

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Menu menu = menuService.findById(id);
        model.addAttribute("menu", menu);
        return "update_menu";
    }

    @PostMapping("/update/{id}")
    public String update(
            @PathVariable("id") Long id,
            @ModelAttribute MenuRequest request,
            Model model
    ){
        Menu updatedMenu = menuService.update(
                id,
                request.name(),
                request.price(),
                request.imageUrl()
        );

        List<Menu> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model model){
        Menu menu = menuService.findById(id);
        menuService.delete(id);
        List<Menu> menus = menuService.findall();
        model.addAttribute("menus", menus);
        return "Menu";
    }


}
