package gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.controller.ProductController;
import gift.domain.Product;
import gift.domain.Products;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private Products products;

    private Product product;
    private Product badProduct;


    @BeforeEach
    void setUp() {
        product = new Product(1L, "TestName", 4500, "http://imageUrl.com");
        badProduct =new Product(null, "TestName", 4500, "http://imageUrl.com");
    }

    @Test
    @DisplayName("Get Test")
    void Get_Test() throws Exception {
        when(products.add(any(Product.class))).thenReturn(true); // 필요한 경우 설정을 추가해야 할 수 있습니다.

        mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
            .andExpect(status().isCreated());

        mvc.perform(get("/product"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
