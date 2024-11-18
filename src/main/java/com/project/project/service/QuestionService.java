package com.project.project.service;

import com.project.project.domain.entity.Question;
import com.project.project.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> questionList(){
        return questionRepository.findAll();
    }
}
