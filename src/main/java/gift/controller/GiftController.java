package gift.controller;


import gift.entity.Gift;
import gift.entity.GiftDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/products")
public class GiftController {
    private GiftDao giftDao;

    GiftController(GiftDao giftDao){
        this.giftDao = giftDao;
    }

    @PostMapping
    public Gift addGift(@RequestBody Gift giftreq){
        Gift gift = giftDao.save(giftreq);
        return gift;
    }
    @GetMapping("/{id}")
    public Gift getGift(@PathVariable Long id){
        return giftDao.findById(id);
    }

    @GetMapping
    public Collection<Gift> getAllGift(){
        return giftDao.findAll();
    }

    @PostMapping("/{id}")
    public Gift updateGift(@PathVariable Long id , @RequestBody Gift giftreq){
        Gift gift = giftDao.updateById(id,giftreq);
        return gift;
    }
    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable Long id){
        giftDao.deleteById(id);
    }
}

