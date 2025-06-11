package com.example.doglogbe.service;

import com.example.doglogbe.entity.CareTip;
import com.example.doglogbe.entity.CareTipResponse;
import com.example.doglogbe.exception.CCareTipNotFoundException;
import com.example.doglogbe.model.CareTipCreateRequest;
import com.example.doglogbe.model.CareTipItem;
import com.example.doglogbe.repository.CareTipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CareTipService {

    private final CareTipRepository careTipRepository;

    //care tip 생성하기(제목,내용,카테고리만 받습니다)
    public void setCareTip(CareTipCreateRequest careTipCreateRequest) {
        careTipRepository.save(new CareTip.Builder(careTipCreateRequest).build());
    }

    //care tip 리스트로 전체 목록 가져오기(컨텐츠는 빠져있습니다)
    public Page<CareTipItem> getCareTips(int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<CareTip> careTips = careTipRepository.findAll(pageable);
        List<CareTipItem> careTipItems = new LinkedList<>();
        for( CareTip careTip: careTips ) {
            careTipItems.add(new CareTipItem.Builder(careTip).build());
        }
        return new PageImpl<>(careTipItems, pageable, careTips.getTotalElements());
    }

    //care tip 단일 항목 가져오기
    public CareTipResponse getCareTip(long id){
        CareTip careTip = careTipRepository.findById(id).orElseThrow(CCareTipNotFoundException::new);
        return new CareTipResponse.Builder(careTip).build();
    }

    //care tip 추천 여부 변경
    public void putCareTipByRecommend(long id){
        CareTip careTip = careTipRepository.findById(id).orElseThrow(CCareTipNotFoundException::new);
        careTip.setRecommend(!careTip.getRecommend());
        careTipRepository.save(careTip);
    }

    //care tip 활성화 여부 변경
    public void putCareTipByEnabled(long id){
        CareTip careTip = careTipRepository.findById(id).orElseThrow(CCareTipNotFoundException::new);
        careTip.setIsEnabled(!careTip.getIsEnabled());
        careTipRepository.save(careTip);
    }

    // 케어팁 search 기능 구현





}
