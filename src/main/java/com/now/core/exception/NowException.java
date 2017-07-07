package com.now.core.exception;

import com.now.core.enums.ResultEnums;

/**
 * 自定义异常
 * Created by Hujh on 2017/5/2.
 */
public class NowException extends RuntimeException{

    private Integer errorCode;

    public NowException(ResultEnums enums) {
        super(enums.getErrorMsg());
        this.errorCode = enums.getErrorCode();
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public NowException(Integer errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
    }
}
