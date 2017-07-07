package com.now.core.handler;

import com.now.domain.Result;
import com.now.core.exception.NowException;
import com.now.util.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理类
 * Created by Hujh on 2017/5/2.
 */
@ControllerAdvice
public class ExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(value=Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if(e instanceof NowException) {
            NowException nowException = (NowException) e;
            return ResultHandler.error(nowException.getErrorCode(), nowException.getMessage());
        }
        logger.error("系统出现异常{}", e);
        return ResultHandler.error(-1, e.getMessage());
    }
}
