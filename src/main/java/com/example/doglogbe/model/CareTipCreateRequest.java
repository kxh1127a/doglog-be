package com.example.doglogbe.model;

import com.example.doglogbe.enums.CareTipCategory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CareTipCreateRequest {
    private CareTipCategory careTipCategory;
    private String title;
    private String content;
    private MultipartFile image;  // 이미지 파일
}
