package com.example.doglogbe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaSearchRequest {
    private Integer page;
    private Integer size;
    private String status;
    private String orderDirection;

    private String byTitle;
    private String byWriter;
}
