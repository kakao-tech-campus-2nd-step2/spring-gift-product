package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.ProductDto;
import gift.service.ProductService;
import jakarta.servlet.ServletException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductService productService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("상품 단건 조회 API 테스트")
    void 상품_단건_조회_API_TEST() throws Exception {
        //given
        ProductDto productDto = new ProductDto("test", 1000, "abc.png");

        //when
        given(productService.findProductById(1L))
                .willReturn(productDto);

        given(productService.findProductById(2L))
                .willThrow(new NoSuchElementException("해당 상품이 존재하지 않습니다."));

        //then
        mvc.perform(get("/api/products/{id}", 1L))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.price").value(1000))
                .andExpect(jsonPath("$.imageUrl").value("abc.png"))
                .andDo(print());

        ServletException servletException = assertThrows(ServletException.class, () -> {
            mvc.perform(get("/api/products/{id}", 2L));
        });

        assertThat(servletException.getMessage()).contains("해당 상품이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("상품 전체 조회 API 테스트")
    void 상품_전체_조회_API_TEST() throws Exception {
        //given
        ProductDto productDto1 = new ProductDto("test1", 1000, "abc.png");
        ProductDto productDto2 = new ProductDto("test2", 1000, "abc.png");
        ProductDto productDto3 = new ProductDto("test3", 1000, "abc.png");
        ProductDto productDto4 = new ProductDto("test4", 1000, "abc.png");
        ProductDto productDto5 = new ProductDto("test5", 1000, "abc.png");

        List<ProductDto> productDtos = new ArrayList<>(Arrays.asList(productDto1, productDto2, productDto3, productDto4, productDto5));

        //when
        given(productService.findAllProducts())
                .willReturn(productDtos);

        //then
        mvc.perform(get("/api/products"))
                .andExpect(jsonPath("$.length()").value(5))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 저장 API 테스트")
    void 상품_저장_API_TEST() throws Exception {
        //given
        ProductDto request = new ProductDto("테스트", 1000, "abc.png");

        String json = objectMapper.writeValueAsString(request);
        //when
        given(productService.addProduct(any())).willReturn(1L);

        //then
        mvc.perform(post("/api/products")
                        .contentType(APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 API 테스트")
    void 상품_수정_API_TEST() throws Exception {
        //given
        ProductDto productDto = new ProductDto("test", 2000, "abc.png");

        //when
        given(productService.updateProduct(1L, 2000))
                .willReturn(productDto);

        given(productService.updateProduct(2L, 2000))
                .willThrow(new NoSuchElementException("해당 상품이 존재하지 않습니다."));

        //then
        mvc.perform(put("/api/products/{id}", 1L)
                        .param("price","2000"))
                .andExpect(jsonPath("$.name").value("test"))
                .andExpect(jsonPath("$.price").value(2000))
                .andExpect(jsonPath("$.imageUrl").value("abc.png"))
                .andDo(print());

        ServletException servletException = assertThrows(ServletException.class, () -> {
            mvc.perform(put("/api/products/{id}", 2L).param("price","2000"));
        });

        assertThat(servletException.getMessage()).contains("해당 상품이 존재하지 않습니다.");
    }

    @Test
    @DisplayName("상품 삭제 API 테스트")
    void 상품_삭제_API_TEST() throws Exception {
        //given

        //when
        given(productService.deleteProduct(1L))
                .willReturn(1L);

        given(productService.deleteProduct(2L))
                .willThrow(new NoSuchElementException("해당 상품이 존재하지 않습니다."));

        //then
        mvc.perform(delete("/api/products/{id}", 1L))
                .andExpect(jsonPath("$.id").value(1))
                .andDo(print());

        ServletException servletException = assertThrows(ServletException.class, () -> {
            mvc.perform(delete("/api/products/{id}", 2L));
        });

        assertThat(servletException.getMessage()).contains("해당 상품이 존재하지 않습니다.");
    }
}