package com.info.sms.enums;

/**
 * Created by Luke 2020/5/14 13:53
 */
public enum  CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public static boolean isExist(Integer type) {
        for(CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()){
            if(commentTypeEnum.getType() == type){
                return true;
            }
        }
        return false;
    }
}
