package gift.controller;

import static org.assertj.core.api.Assertions.assertThat;

import gift.domain.Product;
import java.util.Collection;
import java.util.function.Predicate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class ProductControllerTest {
    private ProductController productController;

    @BeforeEach
    public void setup()
    {
        productController = new ProductController();
    }

    @Test
    @DisplayName("상품 조회 Test(모든 상품)")
    public void TestGetAllProduct() {
        Product product1 = new Product(1L, "카페라떼", 4500,
            "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        productController.createProduct(product1);
        Collection<Product> prod = productController.getAllProduct();

        Assertions.assertEquals(prod.size(), 1);
    }

    @Test
    @DisplayName("상품 조회 Test(id에 따른)")
    public void TestGetProductById() {
        Product product1 = new Product(1L, "아이스 아메리카노", 4500,
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.paris.co.kr%2Fproduct%2Fice-americano%2F&psig=AOvVaw3kcStajcrqwbnaD1--gEAq&ust=1719465381459000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCDqezB-IYDFQAAAAAdAAAAABAE");
        Product product2 = new Product(2L, "카페라떼", 4800,
            "https://www.google.com/imgres?q=%EC%B9%B4%ED%8E%98%EB%9D%BC%EB%96%BC&imgurl=https%3A%2F%2Fcdn.imweb.me%2Fupload%2FS202011028376a98fe86b7%2F44841bf082618.png&imgrefurl=https%3A%2F%2Fwww.brownrabbit.co.kr%2Fcoffee01%2F%3Fq%3DYToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9%26bmode%3Dview%26idx%3D14220292%26t%3Dboard&docid=bYGJvbQ_kffA9M&tbnid=QkS7y1huOBCY8M&vet=12ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA..i&w=1000&h=1000&hcb=2&ved=2ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA");


        productController.createProduct(product1);
        productController.createProduct(product2);
        Product getProduct = productController.getProductbyId(2L);

        Assertions.assertNotEquals(getProduct.getId(), 1L);
        Assertions.assertEquals(getProduct.getId(), 2L);
        Assertions.assertEquals(getProduct.getPrice(), 4800);
        Assertions.assertEquals(getProduct.getName(), "카페라떼");
        Assertions.assertEquals(getProduct.getImageUrl(), "https://www.google.com/imgres?q=%EC%B9%B4%ED%8E%98%EB%9D%BC%EB%96%BC&imgurl=https%3A%2F%2Fcdn.imweb.me%2Fupload%2FS202011028376a98fe86b7%2F44841bf082618.png&imgrefurl=https%3A%2F%2Fwww.brownrabbit.co.kr%2Fcoffee01%2F%3Fq%3DYToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9%26bmode%3Dview%26idx%3D14220292%26t%3Dboard&docid=bYGJvbQ_kffA9M&tbnid=QkS7y1huOBCY8M&vet=12ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA..i&w=1000&h=1000&hcb=2&ved=2ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA");
    }

    @Test
    @DisplayName("상품 추가 Test")
    public void TestAddProduct() {
        Product product1 = new Product(1L, "아이스 아메리카노", 4500,
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.paris.co.kr%2Fproduct%2Fice-americano%2F&psig=AOvVaw3kcStajcrqwbnaD1--gEAq&ust=1719465381459000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCDqezB-IYDFQAAAAAdAAAAABAE");
        ResponseEntity<Product> createProductResponse = productController.createProduct(product1);
        Product addProduct = createProductResponse.getBody();

        Assertions.assertEquals(addProduct.getId(), 1L);
        Assertions.assertEquals(addProduct.getPrice(), 4500);

    }

    @Test
    @DisplayName("상품 수정 Test")
    public void TestUpdatedProduct() {
        Product product1 = new Product(1L, "아이스 아메리카노", 4500,
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.paris.co.kr%2Fproduct%2Fice-americano%2F&psig=AOvVaw3kcStajcrqwbnaD1--gEAq&ust=1719465381459000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCDqezB-IYDFQAAAAAdAAAAABAE");
        Product product2 = new Product(2L, "카페라떼", 4800,
            "https://www.google.com/imgres?q=%EC%B9%B4%ED%8E%98%EB%9D%BC%EB%96%BC&imgurl=https%3A%2F%2Fcdn.imweb.me%2Fupload%2FS202011028376a98fe86b7%2F44841bf082618.png&imgrefurl=https%3A%2F%2Fwww.brownrabbit.co.kr%2Fcoffee01%2F%3Fq%3DYToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9%26bmode%3Dview%26idx%3D14220292%26t%3Dboard&docid=bYGJvbQ_kffA9M&tbnid=QkS7y1huOBCY8M&vet=12ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA..i&w=1000&h=1000&hcb=2&ved=2ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA");

        productController.createProduct(product1);
        ResponseEntity<Product> updatedProductResponse = productController.updateProduct(1L,product2);
        Product updatedProduct = updatedProductResponse.getBody();

        Assertions.assertEquals(updatedProduct.getId(), 2L);
        Assertions.assertEquals(updatedProduct.getPrice(), 4800);
        Assertions.assertEquals(updatedProduct.getName(), "카페라떼");
    }

    @Test
    @DisplayName("상품 삭제 Test")
    public void TestDeleteProduct() {
        Product product1 = new Product(1L, "아이스 아메리카노", 4500,
            "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.paris.co.kr%2Fproduct%2Fice-americano%2F&psig=AOvVaw3kcStajcrqwbnaD1--gEAq&ust=1719465381459000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCOCDqezB-IYDFQAAAAAdAAAAABAE");
        Product product2 = new Product(2L, "카페라떼", 4800,
            "https://www.google.com/imgres?q=%EC%B9%B4%ED%8E%98%EB%9D%BC%EB%96%BC&imgurl=https%3A%2F%2Fcdn.imweb.me%2Fupload%2FS202011028376a98fe86b7%2F44841bf082618.png&imgrefurl=https%3A%2F%2Fwww.brownrabbit.co.kr%2Fcoffee01%2F%3Fq%3DYToxOntzOjEyOiJrZXl3b3JkX3R5cGUiO3M6MzoiYWxsIjt9%26bmode%3Dview%26idx%3D14220292%26t%3Dboard&docid=bYGJvbQ_kffA9M&tbnid=QkS7y1huOBCY8M&vet=12ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA..i&w=1000&h=1000&hcb=2&ved=2ahUKEwiUvdf7wfiGAxXakK8BHbuRB8QQM3oECBkQAA");

        productController.createProduct(product1);
        productController.createProduct(product2);
        productController.deleteProduct(2L);

        Assertions.assertEquals(productController.getAllProduct().size(), 1);
        Assertions.assertEquals(productController.getProductbyId(1L).getName(), "아이스 아메리카노");
        Assertions.assertEquals(productController.getProductbyId(1L).getId(), 1L);

    }
}
