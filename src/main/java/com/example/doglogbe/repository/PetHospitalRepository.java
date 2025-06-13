package com.example.doglogbe.repository;

import com.example.doglogbe.entity.PetHospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetHospitalRepository extends JpaRepository<PetHospital, Long> {
}
