package gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

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
        Product product = new Product(1L, "Product1", 100, "http://image.url");

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(product.id()))
                .andExpect(jsonPath("$.name").value(product.name()))
                .andExpect(jsonPath("$.price").value(product.price()))
                .andExpect(jsonPath("$.imageUrl").value(product.imageUrl()));
    }

    @Test
    void updateProduct() throws Exception {
        Product product = new Product(1L, "Product1", 100, "http://image.url");
        Product updateProduct = new Product(1L, "UpdateProduct", 150, "http://image.url");

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
                .andExpect(jsonPath("$.imageUrl").value(updateProduct.imageUrl()));
    }

    @Test
    void getProduct() throws Exception {
        Product product = new Product(1L, "Product1", 100, "http://image.url");

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/products/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Product1"))
                .andExpect(jsonPath("$.price").value(100))
                .andExpect(jsonPath("$.imageUrl").value("http://image.url"));
    }

    @Test
    void getAllProducts() throws Exception {
        Product product1 = new Product(1L, "Product1", 100, "http://image1.url");
        Product product2 = new Product(2L, "Product2", 200, "http://image2.url");

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
        Product product = new Product(1L, "Product1", 100, "http://image.url");

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