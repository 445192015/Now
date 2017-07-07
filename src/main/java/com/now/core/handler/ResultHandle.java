package com.now.util;

import com.now.domain.Result;

/**
 * 返回结果工具类
 * Created by Hujh on 2017/5/2.
 */
public class ResultHandler {

    //成功返回
    public static Result success() {
        return success(null);
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.setErrorCode(0);
        result.setErrorMsg("请求成功");
        result.setData(object);
        return result;
    }

    //异常返回
    public static Result error(Integer errorCode, String errorMsg) {
        Result result = new Result();
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }
}
