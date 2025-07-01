package com.example.doglogbe.controller;

import com.example.doglogbe.model.QnaCreateRequest;
import com.example.doglogbe.model.QnaItem;
import com.example.doglogbe.model.QnaResponse;
import com.example.doglogbe.model.QnaSearchRequest;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.QnaService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/qna")
public class QnaController {
    private final QnaService qnaService;

    @GetMapping("/api")
    public SingleResult<Page<QnaItem>> getQnaList(@ModelAttribute QnaSearchRequest request) {
        return ResponseService.getSingleResult(qnaService.getQnaList(request));
    }

    @GetMapping("/details/{id}")
    public SingleResult<QnaResponse> getQna(@PathVariable long id) {
        return ResponseService.getSingleResult(qnaService.getQna(id));
    }

    @PostMapping("/answer")
    public SingleResult<QnaResponse> setQna(@RequestBody QnaCreateRequest request) {
        System.out.println(request);
        return ResponseService.getSingleResult(qnaService.setQna(request));
    }
}
