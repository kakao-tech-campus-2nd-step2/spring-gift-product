package gift;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdController {
    private final Map<Long, Product> producsts = new HashMap<>();
}
