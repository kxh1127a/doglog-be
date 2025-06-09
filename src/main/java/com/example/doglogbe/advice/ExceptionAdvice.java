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

    // 예시
    @ExceptionHandler(CAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult customException(HttpServletRequest request, CAlreadyExistsException e) {
        return ResponseService.getFailResult(ResultCode.ALREADY_EXISTS);
    }
}
