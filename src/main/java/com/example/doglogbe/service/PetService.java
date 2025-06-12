package com.example.doglogbe.service;

import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.model.PetItem;
import com.example.doglogbe.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public List<PetItem> getPets() {
        List<Pet> target = petRepository.findAll();

        return target.stream()
                .map(item -> new PetItem.Builder(item).build())
                .collect(Collectors.toList());
    }
}
