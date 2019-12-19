package org.gumplab.common.result;

import lombok.Builder;
import lombok.Data;
import org.gumplab.common.enums.ResultCode;

@Data
@Builder
public class ErrorResult {

    private Integer code;

    private String msg;

    private String exception;

    // 异常枚举封装
    public static ErrorResult fail(ResultCode resultCode, Throwable e) {
        return ErrorResult.builder()
                .msg(resultCode.msg())
                .code(resultCode.code())
                .exception(e.getClass().getName())
                .build();
    }

    // 自定义 message
    public static ErrorResult fail(ResultCode resultCode, Throwable e, String msg) {
        return ErrorResult.builder()
                .msg(msg)
                .code(resultCode.code())
                .exception(e.getClass().getName())
                .build();
    }

    // 自定义 code， message
    public static ErrorResult fail(Integer code, String msg, Throwable e) {
        return ErrorResult.builder()
                .msg(msg)
                .code(code)
                .exception(e.getClass().getName())
                .build();
    }
}
