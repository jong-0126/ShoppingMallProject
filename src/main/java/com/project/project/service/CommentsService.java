package com.project.project.service;

import com.project.project.domain.entity.Comments;
import com.project.project.domain.entity.Question;
import com.project.project.repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentsService {

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Comments> findByQuestion(Question question){
        return commentsRepository.findByQuestion(question);
    }

    public Comments saveComments(Comments comments){
        return commentsRepository.save(comments);
    }

    public void deleteComments(UUID comments_key){
        commentsRepository.deleteById(comments_key);
    }
}
