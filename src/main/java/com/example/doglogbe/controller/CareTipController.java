package com.example.doglogbe.controller;

import com.example.doglogbe.entity.CareTipResponse;
import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.model.CareTipItem;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.CareTipService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/all")
    public ListResult<CareTipItem> getCareTips() {
        return ResponseService.getListResult(careTipService.getCareTips());
    }

    @GetMapping("/detail")
    public SingleResult<CareTipResponse> getCareTip(@RequestParam long careTipId) {
        return ResponseService.getSingleResult(careTipService.getCareTip(careTipId));
    }





}
