package gift.Service;

import Model.Item;
import Model.ItemDTO;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    private final HashMap<Long, Item> itemRepository = new HashMap<>();
    private Long NumId = 1L;

    //Item 저장 메서드
    public void insertItem(ItemDTO itemDTO){
        Item item = new Item(NumId,
            itemDTO.getName(),itemDTO.getPrice(),itemDTO.getImgUrl());
        itemRepository.put(NumId++,item);
    }

    //Item 조회 메서드
    public ItemDTO findItem(Long id){
        if(itemRepository.containsKey(id)) {
            Item item = itemRepository.get(id);
            return new ItemDTO( item.getName(),item.getPrice(), item.getImgUrl());
        }
        return null;
    }
    //Item 목록 반환 메서드
    public HashMap<Long, Item> getList(){
        return itemRepository;
    }


}
