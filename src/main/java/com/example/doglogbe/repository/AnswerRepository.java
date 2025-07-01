package com.example.doglogbe.repository;

import com.example.doglogbe.entity.Answer;
import com.example.doglogbe.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByQuestion(Question question);

}
