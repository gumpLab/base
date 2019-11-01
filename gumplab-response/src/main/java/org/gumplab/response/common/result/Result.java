package org.gumplab.response.common.result;

import lombok.Builder;
import lombok.Data;
import org.gumplab.response.common.enums.ResultCode;

@Data
@Builder
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    public static Result success() {
        return Result.builder().build().builtResult(ResultCode.success);
    }

    public static Result success(Object data) {
        return Result.builder().data(data).build().builtResult(ResultCode.success);
    }

    public static Result fail(ResultCode resultCode) {
        return Result.builder().build().builtResult(resultCode);
    }

    public static Result fail(Integer code, String msg) {
        return Result.builder().code(code).msg(msg).build();
    }

    private Result builtResult(ResultCode success) {
        return Result.builder().code(success.code()).msg(success.msg()).build();
    }

}
