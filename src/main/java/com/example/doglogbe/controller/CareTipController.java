package com.example.doglogbe.controller;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.entity.CareTipResponse;
import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.model.CareTipItem;
import com.example.doglogbe.model.CareTipSearchRequest;
import com.example.doglogbe.model.ActiveCareTipCategory;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.model.result.SingleResult;
import com.example.doglogbe.service.CareTipService;
import com.example.doglogbe.service.ResponseService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/caretip")
public class CareTipController {
    private final CareTipService careTipService;
    private final ResponseService responseService;

    //care tip 생성 (create)
    @PostMapping("/new")
    public CommonResult createCareTip(
            @RequestPart("careTip") CareTipCreateRequest careTipCreateRequest,
            @RequestPart(value = "image", required = false) MultipartFile image) {
        CareTip careTip = careTipService.createCareTip(careTipCreateRequest, image);
        return ResponseService.getSuccessResult();
    }

    //care tip 목록 조회
    @GetMapping("/all")
    public ListResult<CareTipItem> getCareTips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "editDate") String sortBy,
            @RequestParam(defaultValue = "desc") String direction) {
        Page<CareTipItem> careTips = careTipService.getCareTips(page, size, sortBy, direction);
        return responseService.getListResult(careTips.getContent());
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
    public ListResult<CareTipItem> searchCareTips(CareTipSearchRequest searchRequest) {
        Page<CareTipItem> careTips = careTipService.searchCareTips(searchRequest);
        return responseService.getListResult(careTips.getContent());
    }

    @GetMapping("/category")
    public ListResult<ActiveCareTipCategory> getActiveCareTipCategory() {
        return responseService.getListResult(careTipService.getCareTipCategoryActive());
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
