package gift.service;

import gift.domain.Product;
import gift.dto.request.ProductRequestDto;
import gift.dto.response.ProductResponseDto;
import gift.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


    @Test
    @DisplayName("상품 저장 테스트")
    void 상품_저장_테스트(){
        //given
        ProductRequestDto productDto = ProductRequestDto.of("테스트1", 1000, "abc.png");

        Product savedProduct = Product.toEntity(productDto);
        savedProduct.setId(1L);

        given(productRepository.save(any())).willReturn(savedProduct);

        //when
        Long savedId = productService.addProduct(productDto);

        //then
        assertThat(savedId).isEqualTo(savedProduct.getId());
    }

    @Test
    @DisplayName("상품 단건 조회 테스트")
    void 상품_단건_조회_테스트(){
        //given
        Product product = new Product(1L, "테스트1", 1000, "abc.png");
        given(productRepository.findById(1L)).willReturn(Optional.of(product));
        given(productRepository.findById(2L)).willReturn(Optional.empty());

        //when
        ProductResponseDto findProductDto = productService.findProductById(1L);

        //then
        assertThat(product.getName()).isEqualTo(findProductDto.name());
        assertThat(product.getPrice()).isEqualTo(findProductDto.price());
        assertThat(product.getImageUrl()).isEqualTo(findProductDto.imageUrl());
        assertThatThrownBy(() -> productService.findProductById(2L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("전체 상품 조회 테스트")
    void 전체_상품_조회_테스트(){
        //given
        Product product1 = new Product(1L, "테스트1", 1000, "abc.png");
        Product product2 = new Product(2L, "테스트2", 1000, "abc.png");
        Product product3 = new Product(3L, "테스트3", 1000, "abc.png");

        List<Product> products = Arrays.asList(product1, product2, product3);

        given(productRepository.findAll()).willReturn(products);

        //when
        List<ProductResponseDto> productDtos = productService.findAllProducts();

        //then
        assertThat(productDtos.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("상품 수정 테스트")
    void 상품_수정_테스트(){
        //given
        given(productRepository.update(1L, 2000)).willReturn(1L);
        given(productRepository.update(2L, 2000)).willReturn(0L);

        //when
        Long updatedRow = productService.updateProduct(1L, 2000);

        //then
        assertThat(updatedRow).isEqualTo(1L);
        assertThatThrownBy(() -> productService.updateProduct(2L, 2000))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void 상품_삭제_테스트(){
        //given
        given(productRepository.delete(1L)).willReturn(1L);
        given(productRepository.delete(2L)).willReturn(0L);

        //when
        Long deletedRow = productService.deleteProduct(1L);

        //then
        assertThat(deletedRow).isEqualTo(1L);
        assertThatThrownBy(() -> productService.deleteProduct(2L))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 상품은 존재하지 않습니다.");
    }

}