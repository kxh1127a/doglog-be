package com.example.doglogbe.service;

import com.example.doglogbe.entity.Pet;
import com.example.doglogbe.model.PetItem;
import com.example.doglogbe.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public Page<PetItem> getPets(int size, int page) {
        Pageable pageable = PageRequest.of(size, page);
        Page<Pet> target = petRepository.findAll(pageable);

        List<PetItem> result = target.stream()
                .map(item -> new PetItem.Builder(item).build())
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, target.getTotalElements());

    }
}
