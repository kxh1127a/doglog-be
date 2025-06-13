package com.example.doglogbe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareTipSearchRequest {
    private int page;
    private int size;

    private String title;
    private String content;

    private String sortBy;
    private String direction;
    private String filter;
}
