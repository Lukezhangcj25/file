package com.info.sms.exception;

/**
 * Created by Luke 2020/5/8 17:03
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("QUESTIONISNULL","问题不存在!");
    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
