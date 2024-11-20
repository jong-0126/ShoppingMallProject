package com.project.project.controller;

import com.project.project.domain.entity.Item;
import com.project.project.repository.ItemRepository;
import com.project.project.service.ItemService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/item")
    public String getItems(Model model, HttpSession session) {
        List<Item> itemList = itemService.itemList(); // 모든 아이템을 가져오는 서비스 메서드
        model.addAttribute("itemList", itemList);

        Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");

        if(isSuperAdmin != null && isSuperAdmin){
            model.addAttribute("isAdmin", true);
        }else{
            model.addAttribute("isAdmin", false);
        }

        return "item"; // 아이템 리스트를 보여줄 템플릿 이름
    }

    @GetMapping("/itemDetail")
    public String getItemDetail(@RequestParam(name = "item_key") UUID item_key,
                                Model model,HttpSession session) {
        Optional<Item> itemOptional = itemRepository.findById(item_key);
        if (itemOptional.isPresent()) {
            model.addAttribute("item", itemOptional.get());
        } else {
            return "error"; // 아이템을 찾지 못한 경우의 처리
        }

        Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");

        if(isSuperAdmin != null && isSuperAdmin){
            model.addAttribute("isAdmin", true);
        }else{
            model.addAttribute("isAdmin", false);
        }
        return "itemDetail"; // itemDetail.html 템플릿
    }

    @GetMapping("/itemAdd")
    public String add_item() {
        return "itemAdd";
    }

    @PostMapping("/itemAddPro")
    public String add_itemPro( @RequestParam("category") Boolean category,
                               @RequestParam("item_name") String item_name,
                               @RequestParam("item_content") String item_content,
                               @RequestParam("sale_price") Integer sale_price,
                               @RequestParam("cnt") Integer cnt,
                               @RequestParam("item_img") MultipartFile item_img, Model model){

        Item item = new Item();
        item.setCategory(category);
        item.setItem_name(item_name);
        item.setItem_content(item_content);
        item.setSale_price(sale_price);
        item.setCnt(cnt);

        try {
            itemService.itemAdd(item, item_img); // 이미지 파일을 함께 전달
            model.addAttribute("message", "상품이 정상적으로 등록되었습니다.");
            model.addAttribute("searchUrl", "/admin");

        } catch (IOException e) {
            model.addAttribute("message", "상품 등록 중 오류가 발생했습니다: " + e.getMessage());
            model.addAttribute("searchUrl", "/itemAdd");
        }

        return "message";
    }

    @GetMapping("/admin")
    public String itemList(Model model){

        List<Item> itemList = itemService.itemList();
        model.addAttribute("itemList", itemList);
        return "admin";
    }
}


