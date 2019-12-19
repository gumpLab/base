package org.gumplab.common.enums;


public enum ResultCode {

    success(200, "success"),

    error(500, "系统异常, 请联系管理员!"),

    businses_exception(10000, "业务处理异常!");

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
