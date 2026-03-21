package com.pcitc.szgt.contract.exception;

public class VerifyException extends RuntimeException {

    public VerifyException(){}

    public VerifyException(String message){
        super(message);
    }

}
