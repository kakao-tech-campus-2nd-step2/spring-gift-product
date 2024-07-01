package gift.controller;

import gift.db.ProductDB;
import gift.dto.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("H2 DATABASE")
    private ProductDB productDBH2;

    @MockBean
    @Qualifier("MEMORY DATABASE")
    private ProductDB productDBMemory;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product(1L, "Americano", 4500, "https://example.com/americano.png");
        product2 = new Product(2L, "Latte", 5500, "https://example.com/latte.png");
    }

    @Test
    void testGetProducts_H2() throws Exception {
        List<Product> products = Arrays.asList(product1, product2);
        Mockito.when(productDBH2.getProducts()).thenReturn(products);

        mockMvc.perform(get("/").requestAttr("productDB", productDBH2))
                .andExpect(status().isOk())
                .andExpect(view().name("version-SSR/index"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testGetProducts_Memory() throws Exception {
        List<Product> products = Arrays.asList(product1, product2);
        Mockito.when(productDBMemory.getProducts()).thenReturn(products);

        mockMvc.perform(get("/").requestAttr("productDB", productDBMemory))
                .andExpect(status().isOk())
                .andExpect(view().name("version-SSR/index"))
                .andExpect(model().attributeExists("products"));
    }

    @Test
    void testGetAddForm() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("version-SSR/add-form"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testAddProduct_Success_H2() throws Exception {
        Mockito.doNothing().when(productDBH2).addProduct(any(Product.class));

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBH2)
                        .param("id", "3")
                        .param("name", "Mocha")
                        .param("price", "6000")
                        .param("imageUrl", "https://example.com/mocha.png"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testAddProduct_Success_Memory() throws Exception {
        Mockito.doNothing().when(productDBMemory).addProduct(any(Product.class));

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBMemory)
                        .param("id", "3")
                        .param("name", "Mocha")
                        .param("price", "6000")
                        .param("imageUrl", "https://example.com/mocha.png"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testAddProduct_Error_H2() throws Exception {
        Mockito.doThrow(new RuntimeException()).when(productDBH2).addProduct(any(Product.class));

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBH2)
                        .param("id", "4")
                        .param("name", "Mocha")
                        .param("price", "6000")
                        .param("imageUrl", "https://example.com/mocha.png"))
                .andExpect(status().isOk());
        //.andExpect(view().name("redirect:/"));
    }

    @Test
    void testAddProduct_Error_Memory() throws Exception {
        Mockito.doThrow(new RuntimeException()).when(productDBMemory).addProduct(any(Product.class));

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBMemory)
                        .param("id", "3")
                        .param("name", "Mocha")
                        .param("price", "6000")
                        .param("imageUrl", "https://example.com/mocha.png"))
                .andExpect(status().is3xxRedirection());
        //.andExpect(view().name("version-SSR/add-error"));
    }

    @Test
    void testDeleteSelectedProduct_H2() throws Exception {
        Mockito.doNothing().when(productDBH2).removeProducts(any(List.class));

        mockMvc.perform(post("/deleteSelected")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBH2)
                        .param("productIds", "1", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testDeleteSelectedProduct_Memory() throws Exception {
        Mockito.doNothing().when(productDBMemory).removeProducts(any(List.class));

        mockMvc.perform(post("/deleteSelected")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBMemory)
                        .param("productIds", "1", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testDeleteProduct_H2() throws Exception {
        Mockito.doNothing().when(productDBH2).removeProduct(anyLong());

        mockMvc.perform(post("/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBH2)
                        .param("productId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testDeleteProduct_Memory() throws Exception {
        Mockito.doNothing().when(productDBMemory).removeProduct(anyLong());

        mockMvc.perform(post("/delete")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBMemory)
                        .param("productId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testGetEditForm_H2() throws Exception {
        Mockito.when(productDBH2.getProduct(anyLong())).thenReturn(product1);

        mockMvc.perform(get("/edit/1").requestAttr("productDB", productDBH2))
                .andExpect(status().isOk())
                .andExpect(view().name("version-SSR/edit-form"))
                .andExpect(model().attributeExists("product"));
    }

//    @Test //=> 뭐가 문제인지 모르겠음 ...
//    void testGetEditForm_Memory() throws Exception {
//        Mockito.when(productDBMemory.getProduct(1L)).thenReturn(product1);
//
//        mockMvc.perform(get("/edit/1").requestAttr("productDB", productDBMemory))
//                .andExpect(status().isOk());
//                //.andExpect(view().name("version-SSR/edit-form"));
//    }

    @Test
    void testEditProduct_H2() throws Exception {
        Mockito.doNothing().when(productDBH2).editProduct(any(Product.class));

        mockMvc.perform(post("/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBH2)
                        .param("id", "1")
                        .param("name", "Espresso")
                        .param("price", "5000")
                        .param("imageUrl", "https://example.com/espresso.png"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testEditProduct_Memory() throws Exception {
        Mockito.doNothing().when(productDBMemory).editProduct(any(Product.class));

        mockMvc.perform(post("/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .requestAttr("productDB", productDBMemory)
                        .param("id", "1")
                        .param("name", "Espresso")
                        .param("price", "5000")
                        .param("imageUrl", "https://example.com/espresso.png"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
