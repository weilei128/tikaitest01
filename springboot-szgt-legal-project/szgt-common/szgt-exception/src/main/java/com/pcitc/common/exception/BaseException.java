package com.pcitc.common.exception;

import com.pcitc.common.entity.ResultCode;

/**
 * 自定义运行异常
 */
public class BaseException extends RuntimeException {

    private int code;

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BaseException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
