package com.example.doglogbe.controller;

import com.example.doglogbe.model.PetBreedCreateRequest;
import com.example.doglogbe.service.PetBreedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pet-breed")
public class PetBreedController {
    private final PetBreedService petBreedService;

    @PostMapping("/new")
    public void setPetBreed (@RequestBody PetBreedCreateRequest request) {
        petBreedService.setPetBreed(request);
    }
}
