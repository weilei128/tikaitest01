package com.pcitc.szgt.contract.exception;


/**
 * 查询没有发现数据异常
 */
public class NotFoundException extends BaseException {

    public NotFoundException(String message, int code) {
        super(message, code);
    }
}
