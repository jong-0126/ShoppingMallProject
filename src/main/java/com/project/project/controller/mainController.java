
package com.project.project.controller;

import com.project.project.domain.entity.Item;
import com.project.project.repository.ItemRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class mainController {

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/main")
    public String mainView(Model model, HttpSession session){
        // 랜덤으로 상품 가져오기
        List<Item> randomItems = itemRepository.findRandomItems();
        model.addAttribute("items", randomItems);

        Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");

        if(isSuperAdmin != null && isSuperAdmin){
            model.addAttribute("isAdmin", true);
        }else{
            model.addAttribute("isAdmin", false);
        }

        return "main";
    }


}
