package com.project.project.service;

import com.project.project.domain.entity.Cart;
import com.project.project.domain.entity.Item;
import com.project.project.domain.entity.User;
import com.project.project.repository.CartRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addItemCart(User user, Item item, Integer cnt){
        Cart cartItem = cartRepository.findByUserAndItem(user, item)
                .orElse(Cart.builder()
                .user(user)
                .item(item)
                .cnt(0) // 기본 수량
                .date(LocalDateTime.now())
                .build()
                );

        // 수량 증가
        cartItem.setCnt(cartItem.getCnt() + cnt);
        cartRepository.save(cartItem);
    }

    // cart_key로 특정 항목을 장바구니에서 삭제
    public void removeItemFromCart(UUID cart_key) {
        // cart_key에 해당하는 Cart 엔티티를 찾아 삭제
        cartRepository.deleteById(cart_key);
    }
}
