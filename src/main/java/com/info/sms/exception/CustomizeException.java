package com.info.sms.exception;

/**
 * Created by Luke 2020/5/8 16:23
 */
public class CustomizeException extends RuntimeException {

    private Integer code;
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public CustomizeException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
    
    public Integer getCode() {
        return code;
    }
}
