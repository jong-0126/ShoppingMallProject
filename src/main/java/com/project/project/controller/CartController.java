package com.project.project.controller;

import com.project.project.domain.entity.Cart;
import com.project.project.domain.entity.Item;
import com.project.project.domain.entity.User;
import com.project.project.repository.CartRepository;
import com.project.project.repository.ItemRepository;
import com.project.project.repository.UserRepository;
import com.project.project.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CartController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/cart")
    public String CartView(HttpSession session, Model model) {
        // 세션에서 로그인된 사용자 ID 가져오기
        UUID user_id = (UUID) session.getAttribute("user_id");

        if (user_id == null) {
            // 로그인되지 않았으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }

        // 사용자 조회
        Optional<User> userOptional = userRepository.findById(user_id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // 해당 사용자의 장바구니 목록 조회
            List<Cart> cartList = cartRepository.findByUser(user);

            // Model에 장바구니 목록 추가
            model.addAttribute("cartList", cartList);

            Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");

            if(isSuperAdmin != null && isSuperAdmin){
                model.addAttribute("isAdmin", true);
            }else{
                model.addAttribute("isAdmin", false);
            }

            // 장바구니 페이지로 이동
            return "cart";
        }

        // 사용자를 찾지 못했을 경우 오류 페이지로 이동
        return "redirect:/error";
    }

    @PostMapping("/cart/add")
    public String CartAdd(@RequestParam("item_key")UUID item_key,
                          @RequestParam("cnt") Integer cnt,
                          HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");

        if(user_id == null){
            return "redirect:/login ";
        }

        // 사용자 조회
        Optional<User> userOptional = userRepository.findById(user_id);
        // 아이템 조회
        Optional<Item> itemOptional = itemRepository.findById(item_key);

        // 사용자와 아이템이 존재하는지 확인
        if (userOptional.isPresent() && itemOptional.isPresent()) {
            User user = userOptional.get(); // User 객체 추출
            Item item = itemOptional.get(); // Item 객체 추출

            // 장바구니에 추가
            cartService.addItemCart(user, item, cnt);
            model.addAttribute("message", "장바구니에 추가되었습니다.");
            model.addAttribute("searchUrl", "/cart");

        } else {
            // 사용자 또는 아이템이 없는 경우 처리 (예: 에러 메시지 반환)
            model.addAttribute("message", "장바구니 추가 실패했습니다.");
            model.addAttribute("searchUrl", "/main");
        }

    return "message";

    }

    // POST 요청으로 장바구니 항목을 삭제
    @PostMapping("/cart/remove")
    public String removeCartItem(@RequestParam("cart_key") UUID cart_key) {

        // 장바구니에서 특정 항목 삭제
        cartService.removeItemFromCart(cart_key);
        return "redirect:/cart";  // 삭제 후 장바구니 페이지로 리다이렉트
    }
}
