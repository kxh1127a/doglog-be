package com.example.doglogbe.model;

import com.example.doglogbe.enums.CareTipCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CareTipCreateRequest {
    private CareTipCategory careTipCategory;
    private String title;
    private String content;
}
