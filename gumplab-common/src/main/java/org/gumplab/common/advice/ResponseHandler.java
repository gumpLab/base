
package org.gumplab.common.advice;

import org.gumplab.common.utils.JsonUtil;
import org.gumplab.common.result.ErrorResult;
import org.gumplab.common.result.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

// 必须指定 basePackages，否则会导致 swagger 无法解析json而无法使用
@RestControllerAdvice(basePackages = "org.gumplab")
public class ResponseHandler implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 是否支持 advice 功能， 支持(true) 不支持(false)
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object obj, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (obj instanceof ErrorResult) {
            ErrorResult error = (ErrorResult) obj;
            return Result.fail(error.getCode(), error.getMsg());
        } else if (obj instanceof String) {
            return JsonUtil.object2Json(Result.success(obj));
        } else if (obj instanceof Result) {
            return obj;
        }
        return Result.success(obj);
    }
}