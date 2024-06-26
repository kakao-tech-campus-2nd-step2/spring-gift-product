package gift.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gift.model.Product;
import gift.model.ProductRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;
    
    @Test
    @DisplayName("모든 Product 반환 테스트")
    void getAllProducts () throws Exception {

        //then
        Product product1 = new Product(8146027L, "아이스 카페 아메리카노 T", 4500,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");
        Product product2 = new Product(8146028L, "아이스 라떼 T", 5000,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_bbbff9eccc943648797925498bdd8a3.jpg");
        List<Product> allProducts = Arrays.asList(product1, product2);


        //when
        when(productRepository.findAll()).thenReturn(allProducts);

        //then
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(8146027L))
                .andExpect(jsonPath("$[0].name").value("아이스 카페 아메리카노 T"))
                .andExpect(jsonPath("$[0].price").value(4500))
                .andExpect(jsonPath("$[0].imgUrl").value(
                        "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"))
                .andExpect(jsonPath("$[1].id").value(8146028L))
                .andExpect(jsonPath("$[1].name").value("아이스 라떼 T"))
                .andExpect(jsonPath("$[1].price").value(5000))
                .andExpect(jsonPath("$[1].imgUrl").value(
                        "https://st.kakaocdn.net/product/gift/product/20231010111814_bbbff9eccc943648797925498bdd8a3.jpg"));
    }
}