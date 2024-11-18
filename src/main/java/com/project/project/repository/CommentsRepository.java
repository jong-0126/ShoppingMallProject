package com.project.project.repository;

import com.project.project.domain.entity.Comments;
import com.project.project.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<Comments, UUID> {
    List<Comments> findByQuestion(Question question);
}
