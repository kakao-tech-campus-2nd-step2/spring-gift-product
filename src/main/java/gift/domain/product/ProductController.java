package gift.domain.product;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final Map<Long, Product> productRepository = new ConcurrentHashMap<>();
    private final AtomicLong key = new AtomicLong(1);

    @PostConstruct
    public void init() {
        productRepository.put(key.getAndIncrement(), new Product(1L, "아이스 카페 아메리카노 T", 4500, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110563]_20210426095937947.jpg"));
        productRepository.put(key.getAndIncrement(), new Product(2L, "아이스 카페 라떼 T", 5000, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[110569]_20210415143036138.jpg"));
        productRepository.put(key.getAndIncrement(), new Product(3L, "아이스 스타벅스 돌체 라떼 T", 5900, "https://image.istarbucks.co.kr/upload/store/skuimg/2021/04/[128695]_20210426092032110.jpg"));
    }

    @GetMapping("/new")
    public String renderingNewForm() {
        return "new-product";
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product productDTO) {
        Product product = new Product(key.getAndIncrement(), productDTO.getName(),
                                        productDTO.getPrice(), productDTO.getImageUrl());
        productRepository.put(product.getId(), product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping
    public String readAll(Model model) {
        List<Product> productList = new ArrayList<>(productRepository.values());
        model.addAttribute("products", productList);
        return "products";
    }

    @GetMapping("/{productId}")
    public String readById(@PathVariable long productId, Model model) {
        Product product = productRepository.get(productId);

        if (product == null) {
            return "error";
        }
        model.addAttribute("product", product);

        return "product";
    }


    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable long productId, @RequestBody Product productDTO) {
        Product product = productRepository.get(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        productRepository.put(productId, product);

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> delete(@PathVariable long productId) {
        Product product = productRepository.get(productId);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productRepository.remove(productId);

        return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
    }
}
