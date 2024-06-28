package gift.service;

import static org.assertj.core.api.Assertions.assertThat;

import gift.Model.ItemDTO;
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

    @Test
    @DisplayName("상품 수정 기능 검사")
    void test2(){
        ItemDTO itemDTO = new ItemDTO("김치", 1200L, "URL");
        //when
        itemService.insertItem(itemDTO);
        itemService.updateItem(new ItemDTO("커피", 2000L, "nnn"),1L);
        ItemDTO item = itemService.findItem(1L);
        //then
        assertThat(item)
            .isNotNull()
            .extracting("name","price","imgUrl")
            .containsExactly("커피",2000L,"nnn");
    }

    @Test
    @DisplayName("상품 삭제 테스트")
    void test3(){
        ItemDTO itemDTO = new ItemDTO("김치", 1200L, "URL");
        //when
        itemService.insertItem(itemDTO);
        itemService.deleteItem(1L);
        itemService.findItem(1L);
        //then
        assertThat(itemService.findItem(1L))
            .isNull();
    }
}
