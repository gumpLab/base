package org.gumplab.response.common.advice;

import lombok.extern.slf4j.Slf4j;
import org.gumplab.response.common.enums.ResultCode;
import org.gumplab.response.common.exception.BusinessException;
import org.gumplab.response.common.result.ErrorResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 运行时异常统一处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorResult handleThrowable(Throwable e, HttpServletRequest request) {
        log.error("URL-->: {}, 系统异常--> ", request.getRequestURI(), e);
        return ErrorResult.fail(ResultCode.error, e);
    }

    /**
     * 自定义异常处理
     */
    @ExceptionHandler(BusinessException.class)
    public ErrorResult handleBusinessException(Throwable e, HttpServletRequest request) {
        log.error("URL-->: {}, 自定义异常--> ", request.getRequestURI(), e);
        return ErrorResult.fail(ResultCode.businses_exception_test, e);
    }

}
