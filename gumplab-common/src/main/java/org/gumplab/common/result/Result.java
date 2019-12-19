package org.gumplab.common.result;

import lombok.Builder;
import lombok.Data;
import org.gumplab.common.enums.ResultCode;


@Data
@Builder
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    // 统一成功返回
    public static Result success() {
        return builtResult(ResultCode.success);
    }

    // 成功: 包含 Data 数据
    public static Result success(Object data) {
        return builtResult(ResultCode.success, data);
    }

    // 失败: ResultCode 自定义枚举返回值
    public static Result fail(ResultCode resultCode) {
        return builtResult(resultCode);
    }

    // 失败: 自定义 code 和 msg， 不推荐, 应该优先使用枚举
    public static Result fail(Integer code, String msg) {
        return Result.builder().code(code).msg(msg).build();
    }

    // 构建 Result 对象
    private static Result builtResult(ResultCode success, Object data) {
        return Result.builder().code(success.code()).msg(success.msg()).data(data).build();
    }

    private static Result builtResult(ResultCode success) {
        return Result.builder().code(success.code()).msg(success.msg()).build();
    }

}
