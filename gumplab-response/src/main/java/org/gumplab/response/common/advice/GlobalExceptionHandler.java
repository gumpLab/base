package org.gumplab.response.common.advice;

import org.gumplab.response.common.enums.ResultCode;
import org.gumplab.response.common.result.ErrorResult;
import org.gumplab.response.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        return ErrorResult.fail(ResultCode.error, e);
    }

}
