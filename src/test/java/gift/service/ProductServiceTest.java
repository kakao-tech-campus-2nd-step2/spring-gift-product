package gift.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

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

}
