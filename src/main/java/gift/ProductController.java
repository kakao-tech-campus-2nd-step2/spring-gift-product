package gift;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private ProductDAO productDAO = new ProductDAO();

    @GetMapping("/products")
    public ProductRecord[] getAllProducts() {
        ProductRecord record1 = new ProductRecord(
                1,
                "김밥",
                5000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Gimbap_%28pixabay%29.jpg/220px-Gimbap_%28pixabay%29.jpg"
        );
        ProductRecord record2 = new ProductRecord(
                3,
                "라면",
                5000,
                "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e4/Jin_Ramen_%28spicy%29_20210731_002.jpg/220px-Jin_Ramen_%28spicy%29_20210731_002.jpg"
        );

        ProductRecord[] products = new ProductRecord[] {record1, record2};

        return products;
    }

    @PostMapping("/products")
    public ProductRecord addProduct(@RequestBody ProductRecord product) {
        return productDAO.addNewRecord(product);
    }
}
