package org.gumplab.response.common.enums;


public enum ResultCode {

    success(200, "success"),

    error(500, "系统异常, 请联系管理员!"),

    businses_exception_test(10000, "自定义返回值!");

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
