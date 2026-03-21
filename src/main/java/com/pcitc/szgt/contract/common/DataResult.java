package com.pcitc.szgt.contract.common;

public class DataResult<T> {
    private boolean success;

    private Integer code;

    private String msg;

    private T data;

    public static <T> DataResult<T> fail(T data, Integer code, String msg) {
        DataResult<T> result = new DataResult<>();
        result.setData(data);
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        return result;
    }

    public static <T> DataResult<T> success(T data) {
        DataResult<T> result = new DataResult<>();
        result.setData(data);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("ok");
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
