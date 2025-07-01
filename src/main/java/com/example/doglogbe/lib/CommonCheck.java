package com.example.doglogbe.lib;

public class CommonCheck {
    // 휴대폰 번호: 010-0000-0000 형식
    public static boolean checkPhone(String phone) {
        String pattern = "^010-\\d{4}-\\d{4}$";
        return phone.matches(pattern);
    }

    // 아이디: 영문자로 시작하고, 영문자+숫자 조합 5~20자
    public static boolean checkUsername(String username) {
        String pattern = "^[a-zA-Z][a-zA-Z0-9]{3,19}$";
        return username.matches(pattern);
    }

    // 이메일: 일반적인 이메일 형식 (RFC 5322 일부 준수)
    public static boolean checkEmail(String email) {
        String pattern = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(pattern);
    }

    // 비밀번호: 영문자, 숫자 포함 8~20자 (특수문자 포함은 선택 사항)
    public static boolean checkPassword(String password) {
        String pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,20}$";
        return password.matches(pattern);
    }
}
