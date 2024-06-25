package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdController {
    private final Map<Long, Product> products = new HashMap<>();
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return products.get(id);
    }

}
