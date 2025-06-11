package com.example.doglogbe.repository;

import com.example.doglogbe.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countAllByMemberId(long id);
}
