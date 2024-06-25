package gift;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET 메서드 테스트")
    void getProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("POST 메서드 테스트")
    void addProduct() throws Exception {
        String jsonInputString = "{\"id\": 1, \"name\": \"sgoh\", \"price\":999, \"imageUrl\":\"naver.com\"}";

        mockMvc.perform(post("/api/products")
                        .content(jsonInputString)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("PUT 메서드 테스트")
    void updateProduct() throws Exception {
        String jsonInputString = "{\"id\": 1, \"name\": \"sgoh\", \"price\":999, \"imageUrl\":\"naver.com\"}";
        mockMvc.perform(post("/api/products")
                .content(jsonInputString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        String modifiedString = "{\"id\": 1, \"name\": \"hello\", \"price\":999, \"imageUrl\":\"naver.com\"}";
         mockMvc.perform(put("/api/products/1")
                         .content(modifiedString)
                         .contentType(MediaType.APPLICATION_JSON)
                         .accept(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk())
                 .andDo(print());
    }

    @Test
    @DisplayName("DELETE 메서드 테스트")
    void deleteProduct() throws Exception {
        String jsonInputString = "{\"id\": 1, \"name\": \"sgoh\", \"price\":999, \"imageUrl\":\"naver.com\"}";
        mockMvc.perform(post("/api/products")
                .content(jsonInputString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        );

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}