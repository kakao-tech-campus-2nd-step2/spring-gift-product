package gift.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import gift.controller.request.ProductRequest;
import gift.domain.Product;
import gift.domain.ProductRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @DisplayName("모든 상품 정보를 조회해 반환한다.")
    @Test
    void getProducts() throws Exception {
        //given
        given(productRepository.findAll()).willReturn(List.of());

        //when
        productService.getProducts();

        //then
        then(productRepository).should().findAll();
    }

    @DisplayName("상품 하나를 추가한다.")
    @Test
    void addProduct() throws Exception {
        //given
        ProductRequest request = new ProductRequest();
        Product product = new Product();
        given(productRepository.save(product)).willReturn(product);

        //when
        productService.addProduct(request);

        //then
        then(productRepository).should().save(product);
    }

}
