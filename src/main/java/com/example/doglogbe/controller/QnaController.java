package com.example.doglogbe.controller;

import com.example.doglogbe.model.QnaItem;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.QnaService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/qna")
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("/all")
    public SingleResult<Page<QnaItem>> getQnaList(@RequestParam int page,
                                                  @RequestParam int size) {
        return ResponseService.getSingleResult(qnaService.getQnaList(page, size));
    }
}
