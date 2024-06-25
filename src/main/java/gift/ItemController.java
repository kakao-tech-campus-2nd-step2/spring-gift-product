package gift;

import Model.Item;
import java.util.HashMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ItemController {
    HashMap<Long, Item> list = new HashMap<>();

    @GetMapping("/{id}")
    public Item findItem(@PathVariable Long id){
        return list.get(id);
    }

    @GetMapping("list")
    public HashMap<Long,Item> findItemList(){
        return list;
    }

    @PostMapping
    public String CreateItem(@RequestBody Item item){
        if(list.containsKey(item.getId()))
            return "이미 존재하는 id";
        list.put(item.getId(), item);
        return "Ok";
    }
    @PutMapping("/{id}")
    public void updateItem(@PathVariable Long id,@RequestBody Item item){
        list.replace(id,item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        list.remove(id);
    }
}
