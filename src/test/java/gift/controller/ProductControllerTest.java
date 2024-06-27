package gift.controller;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gift.Product;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @MockBean
  private ProductController productController;
  private Product product1;
  private Product product2;

  private Product product3;


  @BeforeEach
  void setUp() {
    product1 = new Product(1L, "아이스 카페 아메리카노 T", 4500,
        "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20231010111814_9a667f9eccc943648797925498bdd8a3.jpg");

    product2 = new Product(2L, "딸기 딜라이트 요거트 블렌디드 T", 6300,
        "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20231010111138_e4c94a012a594e0bb0cf89bae309f37f.jpg");

    product3 = new Product(3L, "딸기 아사이 레모네이드 스타벅스 리프레셔 T", 5900,
        "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20231010111407_7fcb10e99eec4365af527f0bb3d27a0e.jpg");
  }

  @Test
  @DisplayName("특정 ID 상품 조회")
  void testGetProductById() throws Exception {
    Mockito.when(productController.getProductById(1L))
        .thenReturn(new ResponseEntity<>(product1, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("아이스 카페 아메리카노 T"))
        .andExpect(jsonPath("$.price").value(4500));
  }

  @Test
  @DisplayName("모든 상품 조회")
  void testGetProducts() throws Exception {
    List<Product> productList = new ArrayList<>();
    productList.add(product1);
    productList.add(product2);

    Mockito.when(productController.getProducts())
        .thenReturn(new ResponseEntity<>(productList, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1L))
        .andExpect(jsonPath("$[0].name").value("아이스 카페 아메리카노 T"))
        .andExpect(jsonPath("$[0].price").value(4500))
        .andExpect(jsonPath("$[1].id").value(2L))
        .andExpect(jsonPath("$[1].name").value("딸기 딜라이트 요거트 블렌디드 T"))
        .andExpect(jsonPath("$[1].price").value(6300));
  }

  @Test
  @DisplayName("상품 추가")
  void testAddProduct() throws Exception {
    Product newProduct = new Product(4L, "아이스 시그니처 초콜릿 T", 5700,
        "https://img1.kakaocdn.net/thumb/C320x320@2x.fwebp.q82/?fname=https%3A%2F%2Fst.kakaocdn.net%2Fproduct%2Fgift%2Fproduct%2F20231010111611_b94f279ab81e4ac48b57bd0af1a5212c.jpg");

    Mockito.when(productController.addProduct(Mockito.any(Product.class)))
        .thenReturn(new ResponseEntity<>(newProduct, HttpStatus.CREATED));

    mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newProduct)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(4L))
        .andExpect(jsonPath("$.name").value("아이스 시그니처 초콜릿 T"))
        .andExpect(jsonPath("$.price").value(5700));
  }

  @Test
  @DisplayName("상품 수정")
  void testUpdateProduct() throws Exception {
    Product updatedProduct = new Product(1L, "업데이트된 아이스 카페 아메리카노 T", 5000,
        product1.getImageUrl());
    Mockito.when(productController.updateProduct(Mockito.eq(1L), Mockito.any(Product.class)))
        .thenReturn(new ResponseEntity<>(updatedProduct, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updatedProduct)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.name").value("업데이트된 아이스 카페 아메리카노 T"))
        .andExpect(jsonPath("$.price").value(5000));
  }


  @Test
  @DisplayName("상품 삭제")
  void testDeleteProduct() throws Exception {
    Mockito.when(productController.deleteProduct(2L))
        .thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/2"))
        .andExpect(status().isNoContent());
  }
}
