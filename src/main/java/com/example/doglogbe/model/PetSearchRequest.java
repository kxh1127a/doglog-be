package com.example.doglogbe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetSearchRequest {
    private int page;
    private int size;

    private String byName;
    private String byBreed;
}
