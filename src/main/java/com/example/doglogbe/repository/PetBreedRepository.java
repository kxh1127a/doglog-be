package com.example.doglogbe.repository;

import com.example.doglogbe.entity.PetBreed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetBreedRepository extends JpaRepository<PetBreed, Long> {
}
