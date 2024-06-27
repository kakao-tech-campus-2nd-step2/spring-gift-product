package gift.controller;

import gift.model.Product;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Controller;

@Controller
public class ProductViewController {
    private final Map<Long, Product> products = new HashMap<>();
    private static Long sequence = 0L;


}
