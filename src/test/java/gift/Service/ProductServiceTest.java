package gift.Service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import gift.domain.Product;
import gift.domain.ProductDao;
import gift.domain.ProductDaoImpl;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDaoImpl productDao;

    @InjectMocks
    private ProductService productService;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product(1L, "Product1", 100, "imageUrl");
    }

    @Test
    void getAllProducts() {
        //given
        List<Product> products = new ArrayList<>();
        products.add(product);

        given(productDao.findAll()).willReturn(products);

        //when
        List<Product> result = productService.getAllProducts();

        //then
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void getProductById() {
        //given
        given(productDao.findById(1L)).willReturn(product);

        //when
        Product result = productService.getProductById(1L);

        //then
        assertThat(result).isEqualTo(product);
    }

//    @Test
//    void saveProduct() {
//        //given
//
//    }
//
//    @Test
//    void updateProduct() {
//    }
//
//    @Test
//    void deleteProduct() {
//    }
//추후 Dao 클래스 리팩토링 후 작성예정.

}