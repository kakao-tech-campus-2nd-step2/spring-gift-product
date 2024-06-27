package gift.Controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import gift.Model.ItemDTO;
import gift.Service.ItemService;
import java.util.HashMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ItemController.class)
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    ItemDTO itemDTO = new ItemDTO("커피",2000L,"url");
    Long testId = 1L;

    @Test
    @DisplayName("리스트 요청 핸들러 테스트")
    public void test1() throws Exception{
        given(itemService.getList()).willReturn(new HashMap<>());
        mockMvc.perform(get("/product/list"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("list"))
            .andExpect(view().name("list"));
    }
    @Test
    @DisplayName("상품 추가 요청 핸들러 테스트")
    void test2() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                .param("price",String.valueOf(itemDTO.getPrice()))
                .param("name",itemDTO.getName())
                .param("imgUrl",itemDTO.getImgUrl()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/product/list"))
            .andDo(print());
    }
    @Test
    @DisplayName("상품 수정 페이지 요청 헨들러 테스트")
    void test3() throws Exception{
        given(itemService.findItem(testId)).willReturn(itemDTO);

        mockMvc.perform(get("/product/update/{id}",testId))
            .andExpect(status().isOk())
            .andExpect(model().attribute("item",itemDTO))
            .andExpect(model().attribute("id",testId))
            .andExpect(view().name("update"))
            .andDo(print());
    }
}
