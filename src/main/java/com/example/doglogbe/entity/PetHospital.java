package com.example.doglogbe.entity;

import com.example.doglogbe.DoglogBeApplication;
import com.example.doglogbe.type.PetHospitalBusiness;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class PetHospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String animalHospitalName;

    @Column(nullable = true, length = 20)
    private String phoneNumber;

    @Column(nullable = true)
    private String postAddress;

    @Column(nullable = false)
    private String roadNameAddress;

    @Column(nullable = true, length = 10)
    private String careTipNumber;

    @Column(nullable = true)
    private Double xCoordinate;

    @Column(nullable = true)
    private Double yCoordinate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetHospitalBusiness business;

    @Column(nullable = false)
    private Boolean isEnabled;
}
