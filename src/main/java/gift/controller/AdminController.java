package gift.controller;


import gift.model.Gift;
import gift.model.GiftDao;
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
        return "create_form";
    }

    @PostMapping("/admin/create")
    public String giftCreate(@ModelAttribute Gift gift) {
        giftDao.create(gift);
        return "redirect:/admin";
    }


    @GetMapping("/admin/detail/{id}")
    public String detail(Model model,@PathVariable("id") Long id){
        Gift gift = giftDao.findById(id);
        model.addAttribute("gift",gift);
        return "gift_detail";
    }


    @GetMapping("/admin/modify/{id}")
    public String giftModify(Model model,@PathVariable("id") Long id){
        Gift gift = giftDao.findById(id);
        model.addAttribute("gift",gift);
        return "modify_form";

    }
    @PutMapping("/admin/modify/{id}")
    public String giftModify(@PathVariable("id") Long id,
                             @ModelAttribute Gift gift){
        giftDao.updateById(gift,id);
        return "redirect:/admin";

    }
    @DeleteMapping("/admin/delete/{id}")
    public String giftDelete(@PathVariable("id") Long id){
        giftDao.deleteById(id);
        return "redirect:/admin";
    }
}
