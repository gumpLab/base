package org.gumplab.response.common.enums;


import io.swagger.models.auth.In;

public enum ResultCode {

    success(200, "success"),

    error(500, "系统异常, 请联系管理员!");

    Integer code;

    String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code(){
        return this.code;
    }

    public String msg(){
        return this.msg;
    }
}
