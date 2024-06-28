package gift.controller;

import gift.model.Product;
import gift.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product.Builder()
                .id(8146027L)
                .name("아이스 카페 아메리카노 T")
                .price(4500)
                .imageUrl("https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg")
                .build();
    }

    @Test
    void getAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("아이스 카페 아메리카노 T"));
    }

    @Test
    void addProduct() throws Exception {
        doNothing().when(productService).addProduct(any(Product.class));

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 2, \"name\": \"새 상품\", \"price\": 1000, \"imageUrl\": \"https://example.com/newproduct.jpg\"}"))
                .andExpect(status().isOk());

        verify(productService, times(1)).addProduct(any(Product.class));
    }

    @Test
    void updateProduct() throws Exception {
        doNothing().when(productService).updateProduct(eq(8146027L), any(Product.class));

        mockMvc.perform(put("/api/products/8146027")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 8146027, \"name\": \"업데이트된 상품\", \"price\": 5000, \"imageUrl\": \"https://example.com/updatedproduct.jpg\"}"))
                .andExpect(status().isOk());

        verify(productService, times(1)).updateProduct(eq(8146027L), any(Product.class));
    }

    @Test
    void deleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(8146027L);

        mockMvc.perform(delete("/api/products/8146027"))
                .andExpect(status().isOk());

        verify(productService, times(1)).deleteProduct(8146027L);
    }
}
