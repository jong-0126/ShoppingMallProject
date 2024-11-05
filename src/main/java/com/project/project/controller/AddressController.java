package com.project.project.controller;

import com.project.project.domain.entity.Address;
import com.project.project.domain.entity.User;
import com.project.project.repository.AddressRepository;
import com.project.project.repository.UserRepository;
import com.project.project.service.AddressService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class AddressController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/address")
    public String addressView(){
        return "address";
    }

    @PostMapping("/address/register")
    public String addressAdd(@ModelAttribute Address address, HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");
        System.out.println(user_id);
        System.out.println(address.getExtraAddress());
        System.out.println(address.getAddress_key());
        System.out.println(address.getDetailAddress());


        if(user_id == null){
            return "redirect:/login";
        }

        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            addressService.addAddress(user, address);
            model.addAttribute("message", "주소등록 완료");
            model.addAttribute("searchUrl", "/myPage");

            return "message";
        }
        return "redirect:/error";
    }
}
