package com.example.doglogbe.controller;

import com.example.doglogbe.entity.CareTipResponse;
import com.example.doglogbe.model.*;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.CareTipService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/caretip")
public class CareTipController {
    private final CareTipService careTipService;

    //care tip 생성 (create)
    @PostMapping("/new")
    public CommonResult setCareTip(CareTipCreateRequest careTipCreateRequest) {
        careTipService.setCareTip(careTipCreateRequest);
        return ResponseService.getSuccessResult();
    }

    //care tip 목록 조회
    @GetMapping("/all")
    public SingleResult<Page<CareTipItem>> getCareTips(@RequestParam int page ) {
        Page<CareTipItem> careTipItemPage = careTipService.getCareTips(page);
        return ResponseService.getSingleResult(careTipItemPage);
    }

    //care tip enabled true list
    @GetMapping("/all/enabled")
    public SingleResult<Page<CareTipItem>> getCareTipsEnabled(@RequestParam int page) {
        Page<CareTipItem> careTipItemPage = careTipService.getCareTipsIsEnabled(page);
        return ResponseService.getSingleResult(careTipItemPage);
    }

    //care tip 단일 항목 조회
    @GetMapping("/detail")
    public SingleResult<CareTipResponse> getCareTip(@RequestParam long careTipId) {
        return ResponseService.getSingleResult(careTipService.getCareTip(careTipId));
    }

    //care tip recommend (추천여부) 수정
    @PatchMapping("/update/{id}/recommend")
    public CommonResult putCareTipByRecommend(@PathVariable long id) {
        careTipService.putCareTipByRecommend(id);
        return ResponseService.getSuccessResult();
    }

    //care tip enabled (활성화여부) 수정
    @PatchMapping("/update/{id}/enabled")
    public CommonResult putCareTipByEnabled(@PathVariable long id) {
        careTipService.putCareTipByEnabled(id);
        return ResponseService.getSuccessResult();
    }

    //검색 READ
    @GetMapping("/search")
    public SingleResult<Page<CareTipItem>> getCareTipsBySearch(@ModelAttribute CareTipSearchRequest request) {
        Page<CareTipItem> careTipItemPage = careTipService.getCareTipBySearch(request);
        return ResponseService.getSingleResult(careTipItemPage);
    }

    @GetMapping("/category")
    public ListResult<ActiveCareTipCategory> getActiveCareTipCategory() {
        return ResponseService.getListResult(careTipService.getCareTipCategoryActive());
    }

    // 카테고리별 케어팁 목록 조회
    @GetMapping("/category/list")
    public SingleResult<Page<CareTipItem>> getCareTipsByCategory(
            @RequestParam(required = false, defaultValue = "") String category,
            @RequestParam(defaultValue = "0") int page) {
        Page<CareTipItem> careTipItemPage = careTipService.getCareTipsByCategory(category, page);
        return ResponseService.getSingleResult(careTipItemPage);
    }

}
