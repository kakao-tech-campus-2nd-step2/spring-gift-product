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

    @org.junit.jupiter.api.Test
    public void addTest(){
        ProductController productController = new ProductController();
        productController.addProduct(1,"1",1,"1");
        System.out.println(productController.getProducts());
    }

    @org.junit.jupiter.api.Test
    public void deleteTest() {
        ProductController productController = new ProductController();
        Map<Long, Product> products = new HashMap<>();
        Product product = new Product(1,"1",1,"1");
        products.put(1L,product);
        Product product2 = new Product(2,"1",1,"1");
        products.put(2L,product2);
        productController.setProducts(products);
        productController.deleteProduct(1);
        System.out.println(productController.getProducts());
    }

    @org.junit.jupiter.api.Test
    public void modifyTest(){
        ProductController productController = new ProductController();
        Map<Long, Product> products = new HashMap<>();
        Product product = new Product(1,"1",1,"1");
        products.put(1L,product);
        Product product2 = new Product(2,"1",1,"1");
        products.put(2L,product2);
        productController.setProducts(products);
        productController.modifyProduct(1,"modifyname",3,"3");
        System.out.println(productController.getProducts());
    }
}
