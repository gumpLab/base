package org.gumplab.common.advice;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @Async 异步调用方法(无返回值情况)异常处理
 */
@Slf4j
public class SimpleAsyncUncaughtExceptionHandler implements AsyncUncaughtExceptionHandler {

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("{}, {}, {}", ex.getMessage(), method.getName(), JSON.toJSON(params));
        ex.printStackTrace();
    }
}
