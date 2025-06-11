package com.example.doglogbe.controller;

import com.example.doglogbe.entity.PetHospital;
import com.example.doglogbe.service.PetHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet-hospital")
public class PetHospitalController {
    private final PetHospitalService petHospitalService;

    @PostMapping("/all")
    public void setPetHospitalByFile(@RequestBody PetHospital petHospital) {

    }
}
