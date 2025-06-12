package com.example.doglogbe.controller;

import com.example.doglogbe.service.PetHospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pet-hospital")
public class PetHospitalController {
    private final PetHospitalService petHospitalService;

    @PostMapping("/file-upload")
    public String setPetHospitalByFile(@RequestParam("file") MultipartFile file) throws IOException {
        petHospitalService.setPetHospitalByFile(file);
        return "success";
    }
}
