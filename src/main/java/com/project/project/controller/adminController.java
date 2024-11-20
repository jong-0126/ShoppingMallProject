package com.project.project.controller;

import com.project.project.domain.entity.Item;
import com.project.project.domain.entity.OrderItem;
import com.project.project.domain.entity.Orders;
import com.project.project.domain.entity.User;
import com.project.project.repository.OrderItemRepository;
import com.project.project.repository.OrdersRepository;
import com.project.project.repository.UserRepository;
import com.project.project.service.ItemService;
import com.project.project.service.OrdersService;
import com.project.project.service.UserService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class adminController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/admin")
    public String adminView(Model model){

        List<Orders> ordersList = ordersService.ordersList();
        List<Item> itemList = itemService.itemList();
        model.addAttribute("itemList", itemList);
        model.addAttribute("ordersList", ordersList);

        // 사용자 목록 가져오기
        List<User> userList = userService.userList();
        model.addAttribute("userList", userList);

        return "admin";
    }

    @GetMapping("/admin/orders/{id}")
    public String adminOrdersDetail(@PathVariable(name = "id") UUID order_key, Model model) {
        Optional<Orders> ordersOptional = ordersRepository.findOrderWithItems(order_key);

        if (ordersOptional.isPresent()) {
            Orders orders = ordersOptional.get();
            model.addAttribute("order", orders);
        } else {
            model.addAttribute("message", "주문을 찾을 수 없습니다.");
            return "error"; // 에러 페이지
        }

        return "ordersDetail"; // 주문 상세 페이지
    }
}
