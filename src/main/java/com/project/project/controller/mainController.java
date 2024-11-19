package com.project.project.controller;

import com.project.project.domain.entity.Item;
import com.project.project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class mainController {

    @Autowired
    private ItemRepository itemRepository;


    @GetMapping("/main")
    public String mainView(Model model){
        // 랜덤으로 상품 가져오기
        List<Item> randomItems = itemRepository.findRandomItems();
        model.addAttribute("items", randomItems);
        return "main";
    }
}
