package org.gumplab.common.exception;

import lombok.Data;
import org.gumplab.common.enums.ResultCode;

@Data
public class BusinessException extends RuntimeException {

    protected Integer code;

    protected String msg;

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }

    public BusinessException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
