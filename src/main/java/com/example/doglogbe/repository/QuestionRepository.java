package com.example.doglogbe.repository;

import com.example.doglogbe.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countAllByMemberId(long id);

    Page<Question> findAllByIsEnabled(Boolean isEnabled, Pageable pageable);
}
