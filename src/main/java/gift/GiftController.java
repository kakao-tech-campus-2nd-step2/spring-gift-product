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
    public Gift addGift(@RequestBody Gift gift){
        long id = idGenerator.incrementAndGet();
        gift.setId(id);
        gifts.put(id,gift);
        return gift;
    }





}
