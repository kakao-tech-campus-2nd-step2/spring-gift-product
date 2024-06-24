package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api")
public class GiftController {
    private final Map<Long, ProductDTO> producsts = new HashMap<>();

    @GetMapping("/products")
    public ProductDTO getProduct(@RequestParam Long id){
        return producsts.get(id);
    }
}
