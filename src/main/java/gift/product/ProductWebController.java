package gift.product;

import gift.product.model.GetProductRes;
import gift.product.model.PostProductReq;
import gift.product.model.ProductRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductWebController {
    private final ProductRepository productRepository = new ProductRepository();
    private static final Logger logger = LoggerFactory.getLogger(ProductWebController.class);

    @GetMapping
    public String listProducts(Model model) {
        List<GetProductRes> products = productRepository.findAllProduct();
        logger.info("Loaded products: {}", products);
        System.out.println("Loaded products: " + products);
        model.addAttribute("products", products);
        return "products";
    }

    // 상품 추가 페이지
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("productReq", new PostProductReq());
        return "add_product";
    }


}
