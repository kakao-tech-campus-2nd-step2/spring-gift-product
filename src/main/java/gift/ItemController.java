package gift;

import Model.Item;
import java.util.HashMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
