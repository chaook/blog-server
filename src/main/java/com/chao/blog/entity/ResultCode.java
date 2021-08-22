package com.chao.blog.entity;

public enum ResultCode {

    SUCCESS(200, "成功"),
    PERMISSION_NO_ACCESS(300, "无访问权限"),
    INNER_ERROR(500, "内部错误"),
    PARAM_ERROR(600, "参数错误");

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
