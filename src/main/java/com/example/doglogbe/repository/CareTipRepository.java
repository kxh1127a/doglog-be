package com.example.doglogbe.repository;

import com.example.doglogbe.entity.CareTip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareTipRepository extends JpaRepository<CareTip, Long> {
    Page<CareTip> findAllByIsEnabled(Boolean isEnabled, Pageable pageable);
}
