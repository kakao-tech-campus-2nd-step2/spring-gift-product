package gift;

import gift.DTO.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {
    @Test
    void initTest(){
        Product p1 = new Product(1L, "p1", 10000, "/");
        boolean tf = true;
        if(p1.getId() != 1L)
            tf = false;
        if(!p1.getName().equals("p1"))
            tf = false;
        if(p1.getPrice() != 10000)
            tf = false;
        if(!p1.getImageUrl().equals("/"))
            tf = false;
        Assertions.assertTrue(tf);
    }
}
