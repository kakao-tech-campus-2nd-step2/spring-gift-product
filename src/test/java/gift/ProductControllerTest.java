package gift;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.controller.ProductController;
import gift.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)  // 'ProductController' 클래스의 웹 계층을 테스트
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;  //Spring MockMvc 프레임워크를 사용하여 HTTP 요청 및 응답 테스트

    @Autowired
    private ObjectMapper objectMapper;

    private Product sampleProduct;

    @BeforeEach
    void setUp() {
        sampleProduct = new Product(
                null,
                "아이스 카페 아메리카노 T",
                4500L,
                "https://st.kakaocdn.net/product/gift/product/20231010111814_9a667f9eccc943648797925498bdd8a3.jpg"
        );
    }

    @Test
    @DisplayName("상품 조회 테스트")
    void getProductsTest() throws Exception {
        mockMvc.perform(get("/api/products")  // 'api/products' 경로로 GET 요청
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  // 응답 상태 코드가 '200 OK'인지 검증
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));  // 응답 컨텐츠 타입이 JSON인지 검증
    }

    @Test
    @DisplayName("상품 추가 테스트")
    public void addProductTest() throws Exception {

        String productJson = objectMapper.writeValueAsString(sampleProduct);  // 상품 객체를 JSON 문자열로 변환

        mockMvc.perform(post("/api/products")  // '/api/products' 경로로 POST 요청
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())  // 응답 상태 코드가 '201 CREATED'인지 검증
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // 응답에서 상품 정보가 일치하는지 검증
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value(sampleProduct.getName()))
                .andExpect(jsonPath("$.price").value(sampleProduct.getPrice()))
                .andExpect(jsonPath("$.imageUrl").value(sampleProduct.getImageUrl()));
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void updateProductTest() throws Exception {

        String productJson = objectMapper.writeValueAsString(sampleProduct);

        // 상품 추가
        String response = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Product addedProduct = objectMapper.readValue(response, Product.class);

        // 추가되어 있는 상품 정보 변경
        addedProduct.setName("아이스 카페 아메리카노 T updated");
        addedProduct.setPrice(5000L);
        String updatedProductJson = objectMapper.writeValueAsString(addedProduct);

        mockMvc.perform(put("/api/products/" + addedProduct.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // 변경된 상품 정보가 일치하는지 확인
                .andExpect(jsonPath("$.id").value(addedProduct.getId()))
                .andExpect(jsonPath("$.name").value("아이스 카페 아메리카노 T updated"))
                .andExpect(jsonPath("$.price").value(5000L));
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void deleteProductTest() throws Exception {

        String productJson = objectMapper.writeValueAsString(sampleProduct);

        // 상품 추가
        String response = mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Product addedProduct = objectMapper.readValue(response, Product.class);

        // 추가되어 있는 상품 삭제
        mockMvc.perform(delete("/api/products/" + addedProduct.getId())  // '/api/products/{id}' 경로로 DELETE 요청
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

}
