package com.project.project.controller;

import com.project.project.domain.entity.*;
import com.project.project.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class OrdersController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/orders")
    public String odrersView(HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");

        if(user_id == null){
            return "redirect:/login";
        }

        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            List<Cart> cartList = cartRepository.findByUser(user);
            List<Address> addressList = addressRepository.findByUser(user);

            int total_cnt = 0;
            int total_price = 0;

            for (Cart cartItem : cartList) {

                if (cartItem.getItem() != null) {
                    int item_cnt = cartItem.getCnt();
                    int item_price = cartItem.getItem().getSale_price();

                    total_cnt += item_cnt;
                    total_price += item_price * item_cnt;
                }
            }

            model.addAttribute("cartList", cartList);
            model.addAttribute("addressList", addressList);
            model.addAttribute("total_cnt", total_cnt);
            model.addAttribute("total_price", total_price);

            return "orders";
        }
        return "redirect:/error";
    }

    @PostMapping("/orders/submit")
    public String submitOrder(@RequestParam("item_keys[]") List<UUID> itemKeys,
                              @RequestParam("cnt[]") List<Integer> quantities,
                              @RequestParam("total_cnt") int totalCnt,
                              @RequestParam("total_price") int totalPrice,
                              @RequestParam("address_key") UUID address_key,
                              @SessionAttribute("user_id") UUID user_id,
                              Model model) {

        Optional<User> userOptional = userRepository.findById(user_id);
        Optional<Address> addressOptional = addressRepository.findById(address_key);

        User user = userOptional.get();
        Address address = addressOptional.get();

        // Orders 생성
        Orders order = new Orders();
        order.setUser(user);
        order.setAddress(address);
        order.setTotal_price(totalPrice);
        order.setTotal_cnt(totalCnt);
        order.setOrder_date(LocalDate.now());
        ordersRepository.save(order);

        // OrderItem 생성
        for (int i = 0; i < itemKeys.size(); i++) {
            UUID itemKey = itemKeys.get(i);
            Integer quantity = quantities.get(i);

            // Item 정보 가져오기
            Item item = itemRepository.findById(itemKey).orElseThrow(() -> new RuntimeException("Item not found"));

            // OrderItem 생성
            OrderItem orderItem = new OrderItem();
            orderItem.setOrders(order);
            orderItem.setItem(item);
            orderItem.setCnt(quantity);
            orderItem.setPrice(item.getSale_price());
            orderItemRepository.save(orderItem);
        }

        model.addAttribute("message", "주문 성공");
        model.addAttribute("searchUrl", "/main");

        return "message";
    }
}
