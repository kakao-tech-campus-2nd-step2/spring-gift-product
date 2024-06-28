package gift.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.dto.request.ProductRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.repository.ProductRepository;
import gift.service.ProductService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    @DisplayName("상품 전체 조회 API 테스트")
    void 상품_전체_조회_API_TEST() throws Exception {
        //given
        ProductResponseDto productDto1 = ProductResponseDto.of(1L,"test1", 1000, "abc.png");
        ProductResponseDto productDto2 = ProductResponseDto.of(2L,"test2", 1000, "abc.png");
        ProductResponseDto productDto3 = ProductResponseDto.of(3L,"test3", 1000, "abc.png");
        ProductResponseDto productDto4 = ProductResponseDto.of(4L,"test4", 1000, "abc.png");
        ProductResponseDto productDto5 = ProductResponseDto.of(5L,"test5", 1000, "abc.png");

        List<ProductResponseDto> productDtos = new ArrayList<>(Arrays.asList(productDto1, productDto2, productDto3, productDto4, productDto5));

        //when
        given(productService.findAllProducts()).willReturn(productDtos);

        //then
        mvc.perform(get("/products"))
                .andExpect(view().name("manager"))
                .andExpect(model().attribute("productDtos",productDtos))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 저장 GET API 테스트")
    void 상품_저장_GET_API_TEST() throws Exception {
        //given

        //when

        //then
        mvc.perform(get("/products/new"))
                .andExpect(view().name("addForm"))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 저장 POST API 테스트")
    void 상품_저장_POST_API_TEST() throws Exception {
        //given

        //when


        //then
        mvc.perform(post("/products/new")
                        .param("name","test1")
                        .param("price","1000")
                        .param("imageUrl","abc.png"))
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 GET API 테스트")
    void 상품_수정_GET_API_TEST() throws Exception {
        //given
        ProductResponseDto productResponseDto = ProductResponseDto.of(1L, "test", 1000, "abc.png");

        //when
        given(productService.findProductById(1L)).willReturn(productResponseDto);

        //then
        mvc.perform(get("/products/{id}/edit", 1L))
                .andExpect(view().name("editForm"))
                .andExpect(model().attribute("productDto",productResponseDto))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 수정 POST API 테스트")
    void 상품_수정_POST_API_TEST() throws Exception {
        //given

        //when

        //then
        mvc.perform(post("/products/{id}/edit", 1L)
                        .param("price","2000"))
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"))
                .andDo(print());
    }

    @Test
    @DisplayName("상품 삭제 POST API 테스트")
    void 상품_삭제_POST_API_TEST() throws Exception {
        //given

        //when

        //then
        mvc.perform(post("/products/{id}/delete", 1L))
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"))
                .andDo(print());
    }

}