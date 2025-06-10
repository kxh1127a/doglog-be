package com.example.doglogbe.service;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.repository.CareTipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareTipService {

    private final CareTipRepository careTipRepository;

    public void setCareTip(CareTipCreateRequest careTipCreateRequest) {
        careTipRepository.save(new CareTip.Builder(careTipCreateRequest).build());
    }



}
