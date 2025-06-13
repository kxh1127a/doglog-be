package com.example.doglogbe.model;

import com.example.doglogbe.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class QnaResponse {
    private Long id;
    private String writer;
    private String askTitle;
    private String askContent;
    private LocalDate editDate;
    private Boolean isAnswer;

    private Answer
}
