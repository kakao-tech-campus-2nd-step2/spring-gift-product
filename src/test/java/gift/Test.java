package gift;


import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.jupiter.api.Test
    public void getTest() {
        ProductController productController = new ProductController();
        Map<Long, Product> products = new HashMap<>();
        Product product = new Product(1,"1",1,"1");
        products.put(1L,product);
        Product product2 = new Product(2,"1",1,"1");
        products.put(2L,product2);
        productController.setProducts(products);
        System.out.println(productController.getProducts());
    }
}
