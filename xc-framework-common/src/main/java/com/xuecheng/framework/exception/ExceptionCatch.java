package com.xuecheng.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义统一异常捕获类
 *
 * @author：GJH
 * @createDate：2019/9/19
 * @company：洪荒宇宙加力蹲大学
 */
@ControllerAdvice  //控制器增强
public class ExceptionCatch {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionCatch.class);
    /**
     * 定义Map,配置异常类型所对应的错误代码
     */
    private static ImmutableMap<Class<? extends Throwable>, ResultCode> EXCEPTIONS;
    /**
     * 定义Map的builder对象,去构建ImmutableMap
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ResultCode> builder = ImmutableMap.builder();


    static {
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
    }

    /**
     * 设定这个自定义捕获类要捕获CustomException 异常
     */
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public ResponseResult customException(CustomException customException) {
        //记录日志
        LOGGER.error("捕获异常:", customException.getMessage());
        ResultCode resultCode = customException.getResultCode();
        return new ResponseResult(resultCode);
    }

    /**
     * 设定这个自定义捕获类要捕获CustomException 异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseResult exception(Exception exception) {
        //记录日志
        LOGGER.error("捕获异常:", exception.getMessage());
        if (EXCEPTIONS == null) {
            //构建ImmutableMap
            EXCEPTIONS = builder.build();
        }
        //从EXCEPTIONS中找到异常类型所对应的的错误代码,如果找到了将错误代码响应给用户,没找到就响应99999异常(服务器繁忙)
        ResultCode resultCode = EXCEPTIONS.get(exception.getClass());
        if (resultCode != null) {
            return new ResponseResult(resultCode);
        } else {
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }
    }

}
