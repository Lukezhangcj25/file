package com.info.sms.enums;

/**
 * Created by Luke 2020/6/29 14:40
 */
public enum NotificationStatusEnum {

    UNREAD(0),READ(1);
    private int status;

    public int getStatus() {
        return status;
    }
    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
