package com.xuecheng.framework.exception;

import com.xuecheng.framework.model.response.ResultCode;

/**
 * 自定义异常类型
 *
 * @author：GJH
 * @createDate：2019/9/19
 * @company：洪荒宇宙加力蹲大学
 */
public class CustomException extends RuntimeException {
    /**
     * 错误代码
     */
    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
