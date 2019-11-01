package org.gumplab.response.common.advice;

import com.alibaba.druid.support.json.JSONUtils;
import org.gumplab.response.common.result.ErrorResult;
import org.gumplab.response.common.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = "org.gumplab.response")
public class ResponseHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 是否支持 advice 功能， 支持(true) 不支持(false)
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof ErrorResult) {
            ErrorResult error = (ErrorResult) o;
            return Result.fail(error.getCode(), error.getMsg() + "-" + error.getException());
        } else if (o instanceof String) {
            return JSONUtils.toJSONString(o);
        }
        return Result.success(o);
    }
}
