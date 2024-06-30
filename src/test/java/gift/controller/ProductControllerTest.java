package gift.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.Service.ProductService;
import gift.domain.Product;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

//@SpringBootTest
//@AutoConfigureMockMvc
@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("getAllProducts() Test")
    public void testGetAllProducts() throws Exception {
        //given
        Product product1 = new Product(1L, "product1", 100, "imageUrl1");
        Product product2 = new Product(2L, "product2", 200, "imageUrl2");
        List<Product> allProducts = Arrays.asList(product1, product2);
        when(productService.getAllProducts()).thenReturn(allProducts);

        //when & then
        mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(product1.getId()))
            .andExpect(jsonPath("$[0].name").value(product1.getName()))
            .andExpect(jsonPath("$[0].price").value(product1.getPrice()))
            .andExpect(jsonPath("$[0].imageUrl").value(product1.getImageUrl()))
            .andExpect(jsonPath("$[1].id").value(product2.getId()))
            .andExpect(jsonPath("$[1].name").value(product2.getName()))
            .andExpect(jsonPath("$[1].price").value(product2.getPrice()))
            .andExpect(jsonPath("$[1].imageUrl").value(product2.getImageUrl()));
    }

    @Test
    public void testGetProductById() throws Exception {
        //given
        Product product = new Product(1L, "product1", 100, "imageUrl1");
        when(productService.getProductById(1L)).thenReturn(product);

        //when & given
        mockMvc.perform(get("/product/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(product.getId()))
            .andExpect(jsonPath("$.name").value(product.getName()))
            .andExpect(jsonPath("$.price").value(product.getPrice()))
            .andExpect(jsonPath("$.imageUrl").value(product.getImageUrl()));
    }

    @Test
    public void testAddProduct() throws Exception{
        //given
        ObjectMapper objectMapper = new ObjectMapper();
        Product product = new Product(1L,"Product",100,"imageUrl");
        String jsonBody = objectMapper.writeValueAsString(product);

        mockMvc.perform(post("/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonBody))
            .andExpect(status().isCreated());

    }

    @Test
    public void testDeleteProduct() throws Exception{
        mockMvc.perform(delete("/product/1"))
            .andExpect(status().isNoContent());

    }
}
