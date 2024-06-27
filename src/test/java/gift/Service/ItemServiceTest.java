package gift.Service;

import static org.assertj.core.api.Assertions.assertThat;

import Model.ItemDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @DisplayName("상품 추가 기능 검사")
    void Test1() {
        //given
        ItemDTO itemDTO = new ItemDTO("김치", 1200L, "URL");
        //when
        itemService.insertItem(itemDTO);
        ItemDTO item = itemService.findItem(1L);
        //then
        assertThat(item)
            .isNotNull()
            .extracting("name", "price", "imgUrl")
            .containsExactly(itemDTO.getName(), itemDTO.getPrice(), itemDTO.getImgUrl());
    }
}
