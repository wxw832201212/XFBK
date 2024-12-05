package com.fast.qaManager.service.core.enums;

public enum ErrorType {
    SUCCESS(0,"成功"),
    SERVER_ERROR(1,"服务器内部错误"),
    USER_NAME_IS_NULL(2,"用户名不能为空"),
    USER_STUDENT_IS_NULL(3,"学号不能为空"),
    USER_NAME_AND_STUDENT_ID_IS_NULL(4,"学号或用户名错误"),
    USER_SESSION_IS_TIME_OUT(5,"会话过期，请重新登录")
    ;

    private ErrorType(int code, String msg ){
        this.msg = msg;
        this.code = code;
    }

    private String msg;
    private int code;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
