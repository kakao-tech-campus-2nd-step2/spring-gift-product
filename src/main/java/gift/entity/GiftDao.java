package gift.entity;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class GiftDao {
    private final Map<Long, Gift> gifts = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Gift save(Gift giftreq){
        long id = idGenerator.incrementAndGet();
        Gift gift = new Gift(id,giftreq.getName(),giftreq.getPrice(), giftreq.getImageUrl());
        gifts.put(id,gift);
        return gift;
    }

    public Gift findById(Long id){
        if(!gifts.containsKey(id)){
            throw new IllegalArgumentException("상품이 없습니다.");
        }
        return gifts.get(id);
    }

    public Collection<Gift> findAll(){
        return gifts.values();
    }

    public Gift updateById(Long id,Gift giftreq){
        if(!gifts.containsKey(id)){
            throw new IllegalArgumentException("상품이 없습니다.");
        }
        Gift gift = new Gift(id,giftreq.getName(),giftreq.getPrice(), giftreq.getImageUrl());
        gifts.replace(id,gift);
        return gift;
    }
    public void deleteById(Long id){
        if(!gifts.containsKey(id)){
            throw new IllegalArgumentException("상품이 없습니다.");
        }
        gifts.remove(id);
    }
}
