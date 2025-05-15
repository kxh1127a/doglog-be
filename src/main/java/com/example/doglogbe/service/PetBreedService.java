package com.example.doglogbe.service;

import com.example.doglogbe.entity.PetBreed;
import com.example.doglogbe.model.PetBreedCreateRequest;
import com.example.doglogbe.repository.PetBreedRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PetBreedService {
    private final PetBreedRepository petBreedRepository;

    public void setPetBreed (PetBreedCreateRequest request) {
        PetBreed petBreed = new PetBreed();
        petBreed.setBreedName(request.getBreedName());
        petBreedRepository.save(petBreed);
    }
}
