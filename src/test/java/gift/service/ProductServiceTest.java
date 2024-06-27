//package gift.service;
//
//import gift.domain.Product;
//import gift.repository.ProductMemoryRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//class ProductServiceTest {
//
//    private ProductMemoryRepository productRepository = new ProductMemoryRepository();
//    private ProductService productService;
//
//    @BeforeEach
//    void beforeEach(){
//        productService = new ProductService(productRepository);
//    }
//
//    @AfterEach
//    void afterEach(){
//        productRepository.clear();
//    }
//
//    @Test
//    @DisplayName("상품 저장 테스트")
//    void 상품_저장_테스트(){
//        //given
//        ProductDto productDto = new ProductDto("테스트1", 1000, "abc.png");
//
//        //when
//        Long savedId = productService.addProduct(productDto);
//
//        //then
//        Product savedProduct = productRepository.findById(savedId).orElseThrow();
//
//        assertThat(savedProduct.getName()).isEqualTo(productDto.getName());
//        assertThat(savedProduct.getPrice()).isEqualTo(productDto.getPrice());
//        assertThat(savedProduct.getImageUrl()).isEqualTo(productDto.getImageUrl());
//    }
//
//    @Test
//    @DisplayName("상품 단건 조회 테스트")
//    void 상품_단건_조회_테스트(){
//        //given
//        ProductDto productDto = new ProductDto("테스트1", 1000, "abc.png");
//        Long savedId = productService.addProduct(productDto);
//
//        //when
//        ProductDto findProductDto = productService.findProductById(savedId);
//
//        //then
//        assertThat(productDto.getName()).isEqualTo(findProductDto.getName());
//        assertThat(productDto.getPrice()).isEqualTo(findProductDto.getPrice());
//        assertThat(productDto.getImageUrl()).isEqualTo(findProductDto.getImageUrl());
//        assertThatThrownBy(() -> productService.findProductById(savedId + 1))
//                .isInstanceOf(NoSuchElementException.class)
//                .hasMessage("해당 상품은 존재하지 않습니다.");
//    }
//
//    @Test
//    @DisplayName("전체 상품 조회 테스트")
//    void 전체_상품_조회_테스트(){
//        //given
//        ProductDto productDto1 = new ProductDto("테스트1", 1000, "abc.png");
//        ProductDto productDto2 = new ProductDto("테스트2", 1000, "abc.png");
//        ProductDto productDto3 = new ProductDto("테스트3", 1000, "abc.png");
//
//        productService.addProduct(productDto1);
//        productService.addProduct(productDto2);
//        productService.addProduct(productDto3);
//
//        //when
//        List<ProductDto> productDtos = productService.findAllProducts();
//
//        //then
//        assertThat(productDtos.size()).isEqualTo(3);
//    }
//
//    @Test
//    @DisplayName("상품 수정 테스트")
//    void 상품_수정_테스트(){
//        //given
//        ProductDto productDto1 = new ProductDto("테스트1", 1000, "abc.png");
//
//        Long savedId = productService.addProduct(productDto1);
//
//        //when
//        ProductDto modifyProductDto = productService.updateProduct(savedId, 3000);
//        Product findProduct = productRepository.findById(savedId).orElseThrow();
//
//        //then
//        assertThat(modifyProductDto.getPrice()).isEqualTo(3000);
//        assertThat(findProduct.getPrice()).isEqualTo(3000);
//        assertThatThrownBy(() -> productService.updateProduct(savedId+1, 3000))
//                .isInstanceOf(NoSuchElementException.class)
//                .hasMessage("해당 상품은 존재하지 않습니다.");
//    }
//
//    @Test
//    @DisplayName("상품 삭제 테스트")
//    void 상품_삭제_테스트(){
//        //given
//        ProductDto productDto1 = new ProductDto("테스트1", 1000, "abc.png");
//        ProductDto productDto2 = new ProductDto("테스트2", 1000, "abc.png");
//        ProductDto productDto3 = new ProductDto("테스트3", 1000, "abc.png");
//
//        Long savedId = productService.addProduct(productDto1);
//        productService.addProduct(productDto2);
//        productService.addProduct(productDto3);
//
//        //when
//        Long deletedId = productService.deleteProduct(savedId);
//        List<ProductDto> productDtos = productService.findAllProducts();
//
//        //then
//        assertThat(savedId).isEqualTo(deletedId);
//        assertThat(productDtos.size()).isEqualTo(2);
//    }
//
//}