package com.example.doglogbe.service;

import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.model.PetItem;
import com.example.doglogbe.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public List<PetItem> getPets() {
        List<Pet> target = petRepository.findAll();
    }
}
