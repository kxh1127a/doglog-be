package com.example.doglogbe.service;

import com.example.doglogbe.enums.ResultCode;
import com.example.doglogbe.model.result.CommonResult;
import com.example.doglogbe.model.result.ListResult;
import com.example.doglogbe.model.result.SingleResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {

    public static CommonResult getSuccessResult() {
        CommonResult result = new CommonResult();
        setResult(result, true, ResultCode.SUCCESS);
        return result;
    }

    public static <T> SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setResult(result, true, ResultCode.SUCCESS);
        return result;
    }

    public static <T> ListResult<T> getListResult(List<T> data) {
        ListResult<T> result = new ListResult<>();
        result.setList(data);
        setResult(result, true, ResultCode.SUCCESS);

        return result;
    }

    public static CommonResult getFailResult(ResultCode resultCode) {
        CommonResult result = new CommonResult();
        setResult(result, false, resultCode);
        return result;
    }


    private static void setResult(CommonResult commonResult, boolean isSuccess, ResultCode resultCode) {
        commonResult.setIsSuccess(isSuccess);
        commonResult.setCode(resultCode.getCode());
        commonResult.setMessage(resultCode.getMessage());
    }
}
