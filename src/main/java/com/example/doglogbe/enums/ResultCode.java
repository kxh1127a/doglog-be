package com.example.doglogbe.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ResultCode {

    // 0 ~ -99: 공통(Common)
    SUCCESS(0, "성공하였습니다."),
    FAILED(-1, "실패하였습니다."),
    UNAUTHORIZED(-2, "인증이 필요합니다."),
    FORBIDDEN(-3, "권한이 없습니다."),
    INTERNAL_SERVER_ERROR(-4, "서버 오류가 발생했습니다."),

    // -1000 ~ -1999: 회원(User)
    USER_ALREADY_EXISTS(-1000, "이미 등록된 사용자입니다."),
    USER_NOT_FOUND(-1001, "사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(-1002, "아이디 또는 비밀번호가 일치하지 않습니다."),
    INVALID_EMAIL_FORMAT(-1003, "이메일 형식이 올바르지 않습니다."),
    DUPLICATE_USERNAME(-1004, "이미 사용 중인 아이디입니다."),
    EMAIL_VERIFICATION_REQUIRED(-1005, "이메일 인증이 필요합니다."),
    EMAIL_VERIFICATION_FAILED(-1006, "이메일 인증에 실패했습니다."),
    ACCOUNT_DISABLED(-1007, "비활성화된 계정입니다."),
    ACCOUNT_LOCKED(-1008, "계정이 잠겼습니다."),
    LOGIN_FAILED(-1009, "로그인에 실패했습니다."),
    INVALID_LOGIN_PROVIDER(-1010, "잘못된 로그인 제공자입니다."),
    TOKEN_EXPIRED(-1011, "토큰이 만료되었습니다."),
    TOKEN_INVALID(-1012, "토큰이 유효하지 않습니다."),
    REFRESH_TOKEN_EXPIRED(-1013, "리프레시 토큰이 만료되었습니다."),
    UNAUTHORIZED_ACCESS(-1014, "접근 권한이 없습니다."),
    PASSWORD_TOO_WEAK(-1015, "비밀번호 보안 수준이 낮습니다."),
    SOCIAL_ACCOUNT_ALREADY_LINKED(-1016, "이미 연결된 소셜 계정입니다."),
    PASSWORD_CHANGE_FAILED(-1017, "비밀번호 변경에 실패했습니다."),
    USER_DELETION_FAILED(-1018, "회원 탈퇴에 실패했습니다."),
    USER_UPDATE_FAILED(-1019, "회원 정보 수정에 실패했습니다."),
    DUPLICATE_EMAIL(-1020, "이미 사용 중인 이메일입니다."),
    DUPLICATE_PHONE(-1021, "이미 사용 중인 핸드폰입니다."),
    INVALID_USERNAME_FORMAT(-1022, "아이디 형식이 올바르지 않습니다."),
    INVALID_PASSWORD_FORMAT(-1023, "비밀번호 형식이 올바르지 않습니다."),
    INVALID_PHONE_FORMAT(-1024, "핸드폰 형식이 올바르지 않습니다."),
    // 토큰 401 Unauthorized 상황에 대응
    UNAUTHENTICATED(-1025, "인증정보가 없습니다. 로그인이 필요합니다."),
    // 토큰 403 Forbidden 상황에 대응
    ACCESS_DENIED(-1026, "접근 권한이 없는 계정입니다. 관리자 계정으로 로그인 해주세요."),
    // 토큰 인증 실패
    INVALID_TOKEN(-1027, "인증에 실패했습니다. 유효하지 않은 토큰입니다."),

    // -2000 ~ -2999: 반려동물(Pet)
    PET_ALREADY_REGISTERED(-2000, "이미 등록된 반려동물입니다."),
    PET_NOT_FOUND(-2001, "반려동물을 찾을 수 없습니다."),
    PET_DELETE_FAILED(-2002, "반려동물 삭제에 실패했습니다."),
    PET_UPDATE_FAILED(-2003, "반려동물 정보 수정에 실패했습니다."),

    // -3000 ~ -3999: 케어팁(Care Tip)
    // 관리자 등록/수정/삭제
    CARE_TIP_CATEGORY_NOT_FOUND(-3000, "존재하지 않는 케어팁 카테고리입니다."),
    CARE_TIP_ALREADY_EXISTS(-3001, "이미 등록된 케어팁입니다."),
    CARE_TIP_SAVE_FAILED(-3002, "케어팁 등록에 실패했습니다."),
    CARE_TIP_UPDATE_FAILED(-3003, "케어팁 수정에 실패했습니다."),
    CARE_TIP_DELETE_FAILED(-3004, "케어팁 삭제에 실패했습니다."),
    // 사용자 조회
    CARE_TIP_NOT_FOUND(-3010, "해당 케어팁을 찾을 수 없습니다."),
    CARE_TIP_CATEGORY_EMPTY(-3011, "해당 카테고리에 케어팁이 없습니다."),

    // -4000 ~ -4999: 병원 정보(Hospital Info)
    HOSPITAL_NOT_FOUND(-4000, "주변 병원 정보를 찾을 수 없습니다."),
    HOSPITAL_SEARCH_FAILED(-4001, "병원 검색에 실패했습니다."),
    HOSPITAL_DETAIL_FAILED(-4002, "병원 상세 정보를 가져올 수 없습니다."),

    // -5000 ~ -5999: 건강관리 스케줄러(Scheduler)
    SCHEDULE_SAVE_FAILED(-5000, "건강관리 일정 등록에 실패했습니다."),
    SCHEDULE_NOT_FOUND(-5001, "등록된 일정이 없습니다."),
    SCHEDULE_DELETE_FAILED(-5002, "일정 삭제에 실패했습니다."),
    SCHEDULE_UPDATE_FAILED(-5003, "일정 수정에 실패했습니다."),

    // -6000 ~ -6999: 알림(Notification)
    NOTIFICATION_SEND_FAILED(-6000, "알림 전송에 실패했습니다."),
    NOTIFICATION_NOT_FOUND(-6001, "알림 정보를 찾을 수 없습니다."),
    NOTIFICATION_READ_FAILED(-6002, "알림 읽음 처리에 실패했습니다."),

    // -7000 ~ -7999: 파일(File)
    FILE_UPLOAD_FAILED(-7000, "파일 업로드에 실패했습니다."),
    FILE_SIZE_EXCEEDED(-7001, "파일 용량 제한을 초과했습니다."),
    FILE_FORMAT_UNSUPPORTED(-7002, "지원하지 않는 파일 형식입니다."),

    // -8000 ~ -8999: 관리자(Admin)
    ADMIN_LOGIN_FAILED(-8000, "관리자 로그인에 실패했습니다."),
    ADMIN_ACCESS_DENIED(-8001, "관리자 접근 권한이 없습니다."),

    // -9000 ~ -9999: 기타(Etc)
    UNKNOWN_ERROR(-9000, "알 수 없는 오류가 발생했습니다."),

    QUESTION_NOT_FOUND(-9010, "해당 질문(문의)을 찾을 수 없습니다.");

    private final Integer code;
    private final String message;
}
