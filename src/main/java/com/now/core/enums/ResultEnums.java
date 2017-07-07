package com.now.core.enums;

/**
 * Created by Hujh on 2017/5/2.
 */
public enum ResultEnums {
    UNKNOWN_ERROR(-1, "系统出现异常"),
    SUCCESS(0, "操作成功"),

    SUCCESS_10(10, "成功10"),
    SUCCESS_11(11, "成功11"),
    SUCCESS_12(12, "成功12"),


    ERROR_10(-10, "错误10"),
    ERROR_11(-11, "错误11"),
    ERROR_12(-12, "错误12"),
    ;

    private Integer errorCode;

    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    ResultEnums(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
