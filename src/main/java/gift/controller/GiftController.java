package gift.controller;

import gift.model.Gift;
import gift.model.GiftDao;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/products")
public class GiftController {
    private GiftDao giftDao;

    GiftController(GiftDao giftDao){
        this.giftDao = giftDao;
    }

    @PostMapping
    public void addGift(@RequestBody Gift giftreq){
        giftDao.create(giftreq);
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
    public void updateGift(@PathVariable Long id , @RequestBody Gift giftreq){
         giftDao.updateById(giftreq,id);
    }

    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable Long id){
        giftDao.deleteById(id);
    }
}