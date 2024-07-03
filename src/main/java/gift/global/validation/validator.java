package gift.global.validation;

import gift.dto.ProductDTO;
import gift.global.exception.BusinessException;
import gift.global.response.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 유효성 검증 클래스
 */
@Component
public class validator {

    public void validateProduct(ProductDTO productDTO) {
        if (productDTO.getName() == ""
            || productDTO.getPrice() == 0
            || productDTO.getImageUrl() == "") {
            throw new BusinessException(ErrorCode.INVALID_PRODUCT_ARGUMENT);
        }
    }
}
