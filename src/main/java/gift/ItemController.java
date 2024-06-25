package gift;

import Model.Item;
import java.util.HashMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ItemController {
    HashMap<Long, Item> list = new HashMap<Long, Item>();



}
