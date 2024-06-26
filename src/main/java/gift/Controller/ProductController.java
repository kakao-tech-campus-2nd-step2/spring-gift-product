package gift.Controller;

import gift.Model.Product;
import java.util.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final Map<Long, Product> producsts = new HashMap<>();

}
