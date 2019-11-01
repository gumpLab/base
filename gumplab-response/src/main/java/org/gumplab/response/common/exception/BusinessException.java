package org.gumplab.response.common.exception;

import lombok.Data;
import org.gumplab.response.common.enums.ResultCode;

@Data
public class BusinessException extends RuntimeException {

    protected Integer code;

    protected String msg;

    public BusinessException(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }
}
