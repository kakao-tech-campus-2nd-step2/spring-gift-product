package gift.controller;

import gift.domain.Product;
import gift.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void getProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        when(productService.findProducts()).thenReturn(Arrays.asList(product1, product2));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[1].name").value("Product 2"));
    }

    @Test
    void getProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productService.findOne(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Product"));
    }

    @Test
    void getProduct_notFound() throws Exception {
        when(productService.findOne(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void addProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("New Product");
        productDto.setPrice(100);
        productDto.setImageUrl("http://example.com/image.jpg");

        Product product = Product.dtoToEntity(productDto);
        product.setId(1L);

        when(productService.register(any(ProductDto.class))).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Product\",\"price\":100,\"imageUrl\":\"http://example.com/image.jpg\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("New Product"));
    }

    @Test
    void updateProduct() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("Updated Product");
        productDto.setPrice(200);
        productDto.setImageUrl("http://example.com/updated-image.jpg");

        Product product = new Product();
        product.setId(1L);
        product.setName("Old Product");

        when(productService.update(eq(1L), any(ProductDto.class))).thenReturn(Optional.of(product));

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Product\",\"price\":200,\"imageUrl\":\"http://example.com/updated-image.jpg\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"))
                .andExpect(jsonPath("$.price").value(200))
                .andExpect(jsonPath("$.imageUrl").value("http://example.com/updated-image.jpg"));
    }

    @Test
    void updateProduct_notFound() throws Exception {
        when(productService.update(eq(1L), any(ProductDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Product\",\"price\":200,\"imageUrl\":\"http://example.com/updated-image.jpg\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        when(productService.delete(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteProduct_notFound() throws Exception {
        when(productService.delete(1L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNotFound());
    }
}
