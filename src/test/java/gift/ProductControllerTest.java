package gift;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testAddProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1")
                .param("name", "홍길동")
                .param("price", "1000")
                .param("imageUrl", "a"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("추가되었습니다."));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"));
    }

    @Test
    public void testGetProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "1")
            .param("name", "홍길동")
            .param("price", "1000")
            .param("imageUrl", "a"));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("홍길동"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(1000))
            .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("a"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "1")
            .param("name", "홍길동")
            .param("price", "1000")
            .param("imageUrl", "a"));
        String requestBody = "{\"name\":\"이춘식\", \"price\":3000, \"imageUrl\":\"https://image.jpg\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("이춘식"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(3000))
            .andExpect(MockMvcResultMatchers.jsonPath("$.imageUrl").value("https://image.jpg"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("id", "1")
            .param("name", "홍길동")
            .param("price", "1000")
            .param("imageUrl", "a"));
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().string("삭제되었습니다."));
    }
}
