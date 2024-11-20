package com.project.project.controller;

import com.project.project.domain.entity.Item;
import com.project.project.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class adminController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/admin")
    public String itemList(Model model){

        List<Item> itemList = itemService.itemList();
        model.addAttribute("itemList", itemList);
        return "admin";
    }
}
