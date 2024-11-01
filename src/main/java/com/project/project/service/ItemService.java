package com.project.project.service;

import com.project.project.domain.entity.Item;
import com.project.project.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void itemAdd(Item item, MultipartFile item_img) throws IOException{
        // 이미지가 있는 경우 Base64 인코딩
        if (item_img != null && !item_img.isEmpty()) {
            byte[] bytes = item_img.getBytes();
            String base64String = Base64.getEncoder().encodeToString(bytes);
            item.setItem_img(base64String); // Base64 문자열을 item_img에 설정
        } else {
            throw new IOException("이미지가 없습니다.");
        }

        itemRepository.save(item); // Item 저장
    }

    public List<Item> itemList(){

        return itemRepository.findAll();
    }

}
