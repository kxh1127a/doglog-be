package com.example.doglogbe.model;

import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.enums.PetGender;

import java.time.LocalDate;

public class PetItem {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private PetBreed petBreed;
    private Double weight;
    private PetGender gender;
    private String registrationNumber;

    private Long memberId;
    private String memberName;
    private String memberUserName;
}
