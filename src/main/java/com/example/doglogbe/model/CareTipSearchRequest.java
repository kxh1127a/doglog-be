package com.example.doglogbe.model;

import com.example.doglogbe.model.ActiveCareTipCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareTipSearchRequest {
    private String title;
    private String content;
    private ActiveCareTipCategory category;
    private int page = 0;
    private int size = 10;
    private String sortBy = "editDate";
    private String direction = "desc";
}
