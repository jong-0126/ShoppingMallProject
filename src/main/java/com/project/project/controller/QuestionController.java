package com.project.project.controller;

import com.project.project.domain.entity.Question;
import com.project.project.domain.entity.User;
import com.project.project.repository.QuestionRepository;
import com.project.project.repository.UserRepository;
import com.project.project.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Controller
public class QuestionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionService questionService;


    @GetMapping("/question")
    public String questList(Model model){

        model.addAttribute("questionList", questionService.questionList());
        return "questionList";
    }

    @GetMapping("/question/writeForm")
    public String questionWriteForm(){
        return "questionWrite";
    }

    @PostMapping("/question/writePro")
    public String questionWritePro(@ModelAttribute Question question, HttpSession session, Model model){

        UUID user_id = (UUID) session.getAttribute("user_id");
        System.out.println(user_id);

        if(user_id == null){
            return "redirect:/login";
        }

        Optional<User> userOptional = userRepository.findById(user_id);

        User user = userOptional.get();
        question.setUser(user);
        question.setUpload_date(LocalDate.now());
        questionService.write(question);

        model.addAttribute("message", "작성 완료");
        model.addAttribute("searchUrl", "/question");

        return "message";
    }
}
