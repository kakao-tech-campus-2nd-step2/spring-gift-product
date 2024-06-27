package gift.Controller;

import gift.Model.ItemDTO;
import gift.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public String getItemList(Model model){
        model.addAttribute("list",itemService.getList());
        return "list";
    }

    @GetMapping("/create")
    public String getCreateForm(Model model,  ItemDTO itemDTO){
        model.addAttribute("item",itemDTO);
        return "create";
    }

    @PostMapping("/create")
    public String createItem(@ModelAttribute("item") ItemDTO itemDTO){
        itemService.insertItem(itemDTO);
        return "redirect:/product/list";
    }

    @GetMapping("/update/{id}")
    public String updatePage(@PathVariable Long id,Model model){
        ItemDTO itemDTO = itemService.findItem(id);
        model.addAttribute("item",itemDTO);
        model.addAttribute("id",id);
        return "update";
    }
    @PutMapping("/update/{id}")
    public String updateItem(@PathVariable Long id,@ModelAttribute ItemDTO itemDTO){
        itemService.updateItem(itemDTO,id);
        return "redirect:/product/list";
    }
}
