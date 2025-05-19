package com.example.doglogbe.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatusReason {
    LOCKED_BY_PASSWORD_FAILURE("비밀번호 5회 이상 오류로 인한 잠금"),
    WITHDRAWN_BY_USER_REQUEST("회원 요청에 의한 탈퇴 처리"),
    SUSPENDED_BY_VIOLATION("약관 위반으로 인한 계정 정지");

    private final String description;

}
