package gift;

import gift.exception.ValidationException;
import gift.model.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductTest {

    // 정상적인 상황에서 updateAll이 잘 되는지 확인
    @Test
    void testUpdateAllWithGeneralCase() {
        // given
        Product beforeProduct = new Product(1, "n", 0, "i");
        Product afterProduct = new Product(3, "name", 1000, "image.png");

        // when
        beforeProduct.updateAll(afterProduct);

        // then
        Assertions.assertThat(beforeProduct.getId() == afterProduct.getId());
        Assertions.assertThat(beforeProduct.getName().equals(afterProduct.getName()));
        Assertions.assertThat(beforeProduct.getPrice() == afterProduct.getPrice());
        Assertions.assertThat(beforeProduct.getName().equals(afterProduct.getName()));
    }

    // 일부만 업데이트하는 정상적인 상황에서 updateAll이 잘 되는지 확인
    @Test
    void testUpdateSomeWithGeneralCase() {
        // given - id만 업데이트할 예정
        Product beforeProduct = new Product(1, "n", 0, "i");
        Product afterProduct = new Product("3", "", "", "");

        // when
        beforeProduct.updateAll(afterProduct);

        // then - id만 같아야 함.
        Assertions.assertThat(beforeProduct.getId() == afterProduct.getId());
        Assertions.assertThat(!beforeProduct.getName().equals(afterProduct.getName()));
        Assertions.assertThat(beforeProduct.getPrice() != afterProduct.getPrice());
        Assertions.assertThat(!beforeProduct.getName().equals(afterProduct.getName()));
    }

    // 사용자가 넣은 id가 잘못된 경우 예외를 발생시키는지 확인
    @Test
    void testUpdateAllWithIdValidationExcpetion() {
        // given, when, then
        Assertions.assertThatCode(() -> new Product(-1, "name", 1000, "image.png"))
            .isInstanceOf(ValidationException.class).hasMessageContaining("id는 음수일 수 없습니다.");
    }

    // 사용자가 넣은 price가 잘못된 경우 예외를 발생시키는지 확인
    @Test
    void testUpdateAllWithPriceValidationExcpetion() {
        // given, when, then
        Assertions.assertThatCode(() -> new Product(1, "name", -1000, "image.png"))
            .isInstanceOf(ValidationException.class).hasMessageContaining("가격은 음수일 수 없습니다.");
    }
}
