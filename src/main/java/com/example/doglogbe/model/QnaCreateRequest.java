package com.example.doglogbe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaCreateRequest {
    private Long questionId;
    private String comment;
}
