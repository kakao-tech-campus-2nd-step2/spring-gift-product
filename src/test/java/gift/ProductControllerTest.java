package gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void setProduct() throws Exception {
        // Given
        Product product = new Product(1L, "Product1", 100, "http://image.url", 100);

        // When
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)));

        // Then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.id()))
                .andExpect(jsonPath("$.name").value(product.name()))
                .andExpect(jsonPath("$.price").value(product.price()))
                .andExpect(jsonPath("$.imageUrl").value(product.imageUrl()))
                .andExpect(jsonPath("$.amount").value(product.amount()));
    }

    @Test
    void updateProduct() throws Exception {
        Product product = new Product(1L, "Product1", 100, "http://image.url",100);
        Product updateProduct = new Product(1L, "UpdateProduct", 150, "http://image.url",200);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(updateProduct.id()))
                .andExpect(jsonPath("$.name").value(updateProduct.name()))
                .andExpect(jsonPath("$.price").value(updateProduct.price()))
                .andExpect(jsonPath("$.imageUrl").value(updateProduct.imageUrl()))
                .andExpect(jsonPath("$.amount").value(product.amount()));
    }

    @Test
    void getProduct() throws Exception {
        Product product = new Product(1L, "Product1", 100, "http://image.url",100);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.imageUrl").value("http://image.url"))
                .andExpect(jsonPath("$.amount").value(product.amount()));
    }

    @Test
    void getAllProducts() throws Exception {
        Product product1 = new Product(1L, "Product1", 100, "http://image1.url",100);
        Product product2 = new Product(2L, "Product2", 200, "http://image2.url",200);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product1)))
                .andExpect(status().isOk());

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product2)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = new Product(1L, "Product1", 100, "http://image.url",100);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete Successful"));

        mockMvc.perform(delete("/api/products/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(content().string("Delete failed"));
    }
}