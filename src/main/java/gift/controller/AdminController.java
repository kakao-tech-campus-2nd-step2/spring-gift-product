package gift.controller;


import gift.entity.Gift;
import gift.entity.GiftDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class AdminController {
    private GiftDao giftDao;

    AdminController(GiftDao giftDao){
        this.giftDao = giftDao;
    }

    @GetMapping("/admin")
    public String adminHome(Model model){
        Collection<Gift> giftlist = giftDao.findAll();
        model.addAttribute("giftlist",giftlist);
        return "admin";
    }

    @GetMapping("/admin/create")
    public String giftCreate(){
        return "createForm";
    }

    @PostMapping("/admin/create")
    public String giftCreate(@RequestParam(value="name")String name,
                             @RequestParam(value="price")int price,
                             @RequestParam(value ="imageUrl")String imageUrl){
        Gift gift = new Gift(0,name,price,imageUrl);
        giftDao.save(gift);
        return "redirect:/admin";

    }






}
