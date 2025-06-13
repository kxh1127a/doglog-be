package com.example.doglogbe.entity;

import com.example.doglogbe.enums.PetHospitalBusiness;
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
    private String animalHospitalName; // 사업장명

    @Column(nullable = true, length = 20)
    private String phoneNumber; // 소재지전화

    @Column(nullable = true)
    private String postAddress; // 소재지전체주소

    @Column(nullable = true)
    private String roadNameAddress; // 도로명전체주소

    @Column(nullable = true, length = 10)
    private String postCode; // 도로명우편번호

    @Column(nullable = true)
    private Double latitude; // 위도

    @Column(nullable = true)
    private Double longitude; // 경도

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetHospitalBusiness business; // 영업 상태

    @Column(nullable = false)
    private Boolean isEnabled; // 활성 여부
}
