package com.example.doglogbe.controller;

import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.service.CareTipService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/caretip")
public class CareTipController {
    private final CareTipService careTipService;

    @PostMapping("/new")
    public CommonResult setCareTip(CareTipCreateRequest careTipCreateRequest) {
        careTipService.setCareTip(careTipCreateRequest);
        return ResponseService.getSuccessResult();
    }


}
