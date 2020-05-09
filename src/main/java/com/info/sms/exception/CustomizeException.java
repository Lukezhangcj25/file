package com.info.sms.exception;

/**
 * Created by Luke 2020/5/8 16:23
 */
public class CustomizeException extends RuntimeException {

    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {

        this.message = errorCode.getMessage();
    }

    public CustomizeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
