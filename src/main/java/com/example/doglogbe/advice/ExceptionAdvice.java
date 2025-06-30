package com.example.doglogbe.advice;

import com.example.doglogbe.enums.ResultCode;
import com.example.doglogbe.exception.*;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.service.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        return ResponseService.getFailResult(ResultCode.FAILED);
    }

    // 해당 케어팁을 찾을 수 없습니다.
    @ExceptionHandler(CCareTipNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CCareTipNotFoundException e) {
        return ResponseService.getFailResult(ResultCode.CARE_TIP_NOT_FOUND);
    }

    // 해당 유저를 찾을 수 없습니다.
    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CUserNotFoundException e) {
        return ResponseService.getFailResult(ResultCode.USER_NOT_FOUND);
    }

    // 해당 질문(문의)을 찾을 수 없습니다.
    @ExceptionHandler(CQuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CQuestionNotFoundException e) {
        return ResponseService.getFailResult(ResultCode.QUESTION_NOT_FOUND);
    }

    // 이미 사용 중인 아이디입니다.
    @ExceptionHandler(CDuplicateUsernameException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CDuplicateUsernameException e) {
        return ResponseService.getFailResult(ResultCode.DUPLICATE_USERNAME);
    }

    // 이미 사용 중인 이메일입니다.
    @ExceptionHandler(CDuplicateEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CDuplicateEmailException e) {
        return ResponseService.getFailResult(ResultCode.DUPLICATE_EMAIL);
    }

    // 이미 사용 중인 핸드폰입니다.
    @ExceptionHandler(CDuplicatePhoneException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CDuplicatePhoneException e) {
        return ResponseService.getFailResult(ResultCode.DUPLICATE_PHONE);
    }

    // 비밀번호가 일치하지 않습니다.
    @ExceptionHandler(CInvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidPasswordException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_PASSWORD);
    }

    // 아이디 형식이 올바르지 않습니다.
    @ExceptionHandler(CInvalidUsernameFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidUsernameFormatException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_USERNAME_FORMAT);
    }

    // 비밀번호 형식이 올바르지 않습니다.
    @ExceptionHandler(CInvalidPasswordFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidPasswordFormatException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_PASSWORD_FORMAT);
    }

    // 이메일 형식이 올바르지 않습니다.
    @ExceptionHandler(CInvalidEmailFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidEmailFormatException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_EMAIL_FORMAT);
    }

    // 핸드폰 형식이 올바르지 않습니다.
    @ExceptionHandler(CInvalidPhoneFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidPhoneFormatException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_PHONE_FORMAT);
    }

    // 잘못된 로그인 사용자 정보입니다.
    @ExceptionHandler(CInvalidLoginProviderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidLoginProviderException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_LOGIN_PROVIDER);
    }

    // 인증정보가 없습니다. 로그인이 필요합니다.
    @ExceptionHandler(CUnauthenticatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CUnauthenticatedException e) {
        return ResponseService.getFailResult(ResultCode.UNAUTHENTICATED);
    }

    // 접근 권한이 없습니다.
    @ExceptionHandler(CAccessDeniedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CAccessDeniedException e) {
        return ResponseService.getFailResult(ResultCode.ACCESS_DENIED);
    }

    // 인증에 실패했습니다. 유효하지 않은 토큰입니다.
    @ExceptionHandler(CInvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CInvalidTokenException e) {
        return ResponseService.getFailResult(ResultCode.INVALID_TOKEN);
    }
}
