package com.example.doglogbe.model;

import com.example.doglogbe.entity.CareTipCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CareTipCreateRequest {
    private CareTipCategory careTipCategory;
    private String title;
    private String content;
    private LocalDate editDate;
}
