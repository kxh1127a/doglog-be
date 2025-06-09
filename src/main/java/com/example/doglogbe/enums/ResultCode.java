package com.example.doglogbe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {

    SUCCESS(0, "성공하였습니다."),
    FAILED(-1, "실패하였습니다."),

    // 예시
    ALREADY_EXISTS(-1000, "이미 등록되어 있습니다.");



    private final Integer code;
    private final String message;
}
