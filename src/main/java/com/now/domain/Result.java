package com.now.domain;

/**
 * 请求返回对象
 * Created by Hujh on 2017/5/2.
 */
public class Result<T> {

    private Integer errorCode;

    private String errorMsg;

    private T data;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
