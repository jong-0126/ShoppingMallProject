package com.project.project.repository;

import com.project.project.domain.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> findById(UUID question_key);
}
