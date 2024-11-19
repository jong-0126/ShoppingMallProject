package com.project.project.controller;

import com.project.project.domain.entity.Comments;
import com.project.project.domain.entity.Question;
import com.project.project.domain.entity.User;
import com.project.project.repository.CommentsRepository;
import com.project.project.repository.QuestionRepository;
import com.project.project.repository.UserRepository;
import com.project.project.service.CommentsService;
import com.project.project.service.QuestionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class QuestionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CommentsRepository commentsRepository;


    @GetMapping("/question")
    public String questionList(Model model, HttpSession session){

        model.addAttribute("questionList", questionService.questionList());

        Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");

        if(isSuperAdmin != null && isSuperAdmin){
            model.addAttribute("isAdmin", true);
        }else{
            model.addAttribute("isAdmin", false);
        }
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
        questionRepository.save(question);

        model.addAttribute("message", "작성 완료");
        model.addAttribute("searchUrl", "/question");

        return "message";
    }

    @GetMapping("/question/view")
    public String questionView(@RequestParam(name = "id") UUID question_key, Model model, HttpSession session){

        Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");
        UUID user_id = (UUID) session.getAttribute("user_id");
        model.addAttribute("sessionUserId", user_id);

        System.out.println(isSuperAdmin);

        if(isSuperAdmin != null && isSuperAdmin){
            model.addAttribute("isAdmin", true);
        }else{
            model.addAttribute("isAdmin", false);
        }

        Optional<Question> questionOptional = questionRepository.findById(question_key);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            model.addAttribute("question", question);

            List<Comments> comments = commentsRepository.findByQuestion(question);
            model.addAttribute("comments", comments);
        }else {
            model.addAttribute("message", "문의사항을 찾을 수 없습니다.");
            model.addAttribute("searchUrl", "/question");
            return "message";
        }

        return "questionView";
    }

    @GetMapping("/question/modify/{id}")
    public String questionModify(@PathVariable("id") UUID question_key, Model model){

        Optional<Question> questionOptional = questionRepository.findById(question_key);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            model.addAttribute("question", question);
            return "questionModify";
        }
        return "redirect:/error";
    }

    @PostMapping("/question/update/{id}")
    public String questionUpdate(@PathVariable("id") UUID question_key, Question question){

        Optional<Question> questionOptional = questionRepository.findById(question_key);

        if(questionOptional.isPresent()){
            Question question1 = questionOptional.get();
            question1.setTitle(question.getTitle());
            question1.setContent(question.getContent());
            questionRepository.save(question1);
            return "redirect:/question";
        }
        return "redirect:/error";
    }

    @GetMapping("/question/delete/{id}")
    public String questionDelete(@PathVariable("id") UUID question_key, Model model){

        Optional<Question> questionOptional = questionRepository.findById(question_key);
        if(questionOptional.isPresent()){
            Question question = questionOptional.get();
            questionRepository.delete(question);
            model.addAttribute("message", "삭제 성공");
            model.addAttribute("searchUrl", "/question");
            return "message";
        }
        return "redirect:/error";
    }


    @PostMapping("/comment/{id}")
    public String addComment(@PathVariable("id") UUID question_key,
                             @RequestParam(name = "content") String content,
                             HttpSession session) {
        // 세션에서 사용자 정보 가져오기
        Boolean isSuperAdmin = (Boolean) session.getAttribute("isSuperAdmin");
        UUID user_id = (UUID) session.getAttribute("user_id");

        System.out.println("isSuperAdmin: " + isSuperAdmin);

        // 관리자 여부 확인
        if (isSuperAdmin != null && isSuperAdmin) {
            Optional<Question> questionOptional = questionRepository.findById(question_key);
            Optional<User> userOptional = userRepository.findById(user_id);

            Question question = questionOptional.get();
            User user = userOptional.get();

            Comments comment = Comments.builder()
                    .user(user)
                    .question(question)
                    .content(content)
                    .build();

            commentsService.saveComments(comment);
        } else {
            // 관리자 권한이 없을 경우 처리
            return "redirect:/main"; // 권한 없음을 알리는 페이지로 리다이렉트
        }

        return "redirect:/question/view?id=" + question_key;
    }


}
