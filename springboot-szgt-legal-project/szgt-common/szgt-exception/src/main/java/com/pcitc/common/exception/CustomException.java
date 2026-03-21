package com.pcitc.common.exception;


import com.pcitc.common.entity.ResultCode;

/**
 * 查询没有发现数据异常
 */
public class CustomException extends BaseException {


    public CustomException() {
        this(ResultCode.PARAM_MISS);
    }

    public CustomException(String message, int code) {
        super(message, code);
    }

    public CustomException(ResultCode resultCode) {
        super(resultCode);
    }
}
