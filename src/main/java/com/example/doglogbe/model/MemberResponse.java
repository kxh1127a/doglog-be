package com.example.doglogbe.model;

import com.example.doglogbe.entity.Member;
import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.enums.PetGender;
import com.example.doglogbe.interfaces.CommonModelBuilder;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberResponse {
    private Long id;
    private String name;
    private String userName;
    private String email;
    private String phone;
    private LocalDate createdAt;

    private String petName;
    private String petProfileImageUrl;
    private LocalDate petBirthDate;
    private PetGender petGender;
    private Double petWeight;
    private PetBreed petBreed;

    private Integer tipLikeCount;
    private Integer questionCount;
}
