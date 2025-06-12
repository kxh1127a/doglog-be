package com.example.doglogbe.service;

import com.example.doglogbe.entity.Question;
import com.example.doglogbe.model.QnaItem;
import com.example.doglogbe.repository.AnswerRepository;
import com.example.doglogbe.repository.QuestionRepository;
import io.swagger.v3.oas.models.links.Link;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QnaService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;


    public Page<QnaItem> getQnaList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Question> target = questionRepository.findAllByIsEnabled(true, pageable);

        List<QnaItem> result = target.stream()
                .map(item->new QnaItem.Builder(item).build())
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, target.getTotalElements());


    }
}
