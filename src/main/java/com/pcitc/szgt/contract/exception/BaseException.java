package com.pcitc.szgt.contract.exception;

/**
 * 自定义运行异常
 */
public class BaseException extends RuntimeException {

    private int code;

    public BaseException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
