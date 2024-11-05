package com.project.project.controller;

import com.project.project.domain.entity.Address;
import com.project.project.domain.entity.User;
import com.project.project.repository.AddressRepository;
import com.project.project.repository.UserRepository;
import com.project.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class myPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/myPage")
    public String myPageView(HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");

        if(user_id == null){
            return "redirect:/login";
        }

        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()){
            User user = userOptional.get();

            List<Address> addressList = addressRepository.findByUser(user);

            model.addAttribute("addressList", addressList);
            model.addAttribute("user", user);

            return "myPage";

        }

        return "redirect:/login";
    }

    @GetMapping("/user/update")
    public String userUpdateView(HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");

        if(user_id == null){
            return "redirect:/login";
        }

        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            model.addAttribute("user", user);
            return "userUpdate";

        }

        return "redirect:/error";
    }

    @PostMapping("/user/updatePro")
    public String userUpdate(@ModelAttribute User user, HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");

        if(user_id == null){
            return "redirect:/login";
        }

        Optional<User> userOptional = userRepository.findById(user_id);

        if(userOptional.isPresent()){
            User userUpdate = userOptional.get();

            userUpdate.setName(user.getName());
            userUpdate.setEmail(user.getEmail());
            userUpdate.setTel(user.getTel());


            if(!user.getPassword().isEmpty()){
                userUpdate.setPassword(user.getPassword());
            }

            userRepository.save(userUpdate);

            model.addAttribute("message", "회원정보가 수정되었습니다.");
            model.addAttribute("searchUrl", "/myPage");

            return "message";
        }
        return "redirect:/error";
    }
}
