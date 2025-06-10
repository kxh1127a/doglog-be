package com.example.doglogbe.service;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.model.CareTipItem;
import com.example.doglogbe.repository.CareTipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CareTipService {

    private final CareTipRepository careTipRepository;

    public void setCareTip(CareTipCreateRequest careTipCreateRequest) {
        careTipRepository.save(new CareTip.Builder(careTipCreateRequest).build());
    }

    public List<CareTipItem> getCareTips() {
        List<CareTip> careTips = careTipRepository.findAll();
        List<CareTipItem> careTipItems = new LinkedList<>();
        for( CareTip careTip: careTips ) {
            careTipItems.add(new CareTipItem.Builder(careTip).build());
        }
        return careTipItems;
    }



}
