package com.example.doglogbe.model;

import com.example.doglogbe.enums.PetHospitalBusiness;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetHospitalCreateRequest {
    private String animalHospitalName;
    private String phoneNumber;
    private String postAddress;
    private String roadNameAddress;
    private String postCode;
    private Double latitude;
    private Double longitude;
    private PetHospitalBusiness business;
    private Boolean isEnabled;
}
