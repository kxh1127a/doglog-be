package com.example.doglogbe.model;

import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.enums.PetGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PetResponse {
    private String petName;
    private String petProfileImageUrl;
    private LocalDate petBirthDate;
    private PetGender petGender;
    private Double petWeight;
    private PetBreed petBreed;

    public PetResponse(Pet pet) {
        this.petName = pet.getName();
        this.petProfileImageUrl = pet.getProfileImageUrl();
        this.petBirthDate = pet.getBirthDate();
        this.petGender = pet.getGender();
        this.petWeight = pet.getWeight();
        this.petBreed = pet.getPetBreed();
    }
}