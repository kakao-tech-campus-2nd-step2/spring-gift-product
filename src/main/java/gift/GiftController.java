package gift;


import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class GiftController {
    private final Map<Long,Gift> gifts = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    @GetMapping
    public Collection<Gift> getAllgifts(){
        return gifts.values();
    }

    @PostMapping
    public Gift addGift(@RequestBody Gift giftreq){
        long id = idGenerator.incrementAndGet();
        Gift gift = makeGiftComponent(id,giftreq);
        gifts.put(id,gift);
        return gift;
    }
    @PutMapping("/{id}")
    public Gift updateGift(@PathVariable Long id,@RequestBody Gift giftreq){
        if(!gifts.containsKey(id)){
            throw new IllegalArgumentException("찾는 상품이 없습니다!");
        }
        Gift gift = makeGiftComponent(id,giftreq);
        gifts.put(id,gift);
        return gift;
    }
    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable Long id){
        gifts.remove(id);
    }
    @PostMapping("/receive")
    public String age(@RequestParam String name) {
        System.out.println("결과 " + name);
        return "result";
    }
    private Gift makeGiftComponent(Long id,Gift giftreq){
        return new Gift(id, giftreq.name(), giftreq.price(), giftreq.imageUrl());
    }

}

