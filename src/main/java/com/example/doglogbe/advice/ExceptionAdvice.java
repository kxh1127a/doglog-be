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
}
